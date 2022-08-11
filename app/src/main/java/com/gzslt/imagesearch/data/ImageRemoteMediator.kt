package com.gzslt.imagesearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gzslt.imagesearch.common.extension.exhaustive
import com.gzslt.imagesearch.data.api.ApiClient
import com.gzslt.imagesearch.data.db.AppDatabase
import com.gzslt.imagesearch.data.db.model.RemoteKeyDataModel
import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple
import com.gzslt.imagesearch.data.mapper.toDataModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val query: String,
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
) : RemoteMediator<Int, ImageListItemTuple>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageListItemTuple>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                getRemoteKeyClosestToCurrentPosition(state)?.nextKey?.minus(1)
                    ?: FLICKR_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                remoteKey?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                remoteKey?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
        }.exhaustive

        try {
            val apiResponse = apiClient.getByText(
                query = query,
                page = page,
                itemsPerPage = state.config.pageSize
            )

            val imageListApiModel = apiResponse.imageListApiModel
            val endOfPaginationReached = imageListApiModel.imageList.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeyDao().clearRemoteKeys()
                    appDatabase.imageDao().deleteImages()
                }
                val prevKey = if (page == FLICKR_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = imageListApiModel.imageList.map {
                    RemoteKeyDataModel(imageId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.remoteKeyDao().insertAll(keys)
                appDatabase.imageDao().insertImageList(
                    imageListApiModel.imageList.map {
                        it.toDataModel()
                    }
                )
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ImageListItemTuple>
    ): RemoteKeyDataModel? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { image ->
                appDatabase.remoteKeyDao().getRemoteKeyByImageId(image.imageId)
            }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ImageListItemTuple>
    ): RemoteKeyDataModel? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { image ->
                appDatabase.remoteKeyDao().getRemoteKeyByImageId(image.imageId)
            }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ImageListItemTuple>
    ): RemoteKeyDataModel? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.imageId?.let { imageId ->
                appDatabase.remoteKeyDao().getRemoteKeyByImageId(imageId)
            }
        }

    companion object {
        private const val FLICKR_STARTING_PAGE_INDEX = 1
    }
}
