package com.gzslt.imagesearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gzslt.imagesearch.data.api.ApiClient
import com.gzslt.imagesearch.data.db.AppDatabase
import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultStream(query: String): Flow<PagingData<ImageListItemTuple>> =
        Pager(
            config = PagingConfig(
                initialLoadSize = NETWORK_PAGE_SIZE,
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = ImageRemoteMediator(query, apiClient, appDatabase),
        ) {
            appDatabase.imageDao().getImages()
        }.flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
