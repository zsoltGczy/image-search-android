package com.gzslt.imagesearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gzslt.imagesearch.data.api.ApiClient
import com.gzslt.imagesearch.data.db.AppDatabase
import com.gzslt.imagesearch.data.db.tuple.ImageDetailsTuple
import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple
import com.gzslt.imagesearch.data.preferences.QueryPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
    private val queryPreferences: QueryPreferences,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultStream(query: String): Flow<PagingData<ImageListItemTuple>> =
        Pager(
            config = PagingConfig(
                initialLoadSize = NETWORK_PAGE_SIZE,
                pageSize = NETWORK_PAGE_SIZE,
            ),
            remoteMediator = ImageRemoteMediator(query, apiClient, appDatabase),
        ) {
            queryPreferences.saveQuery(query)
            appDatabase.imageDao().getImages()
        }.flow

    fun getSavedQueryOrDefault(): String = queryPreferences.getSavedQuery() ?: DEFAULT_QUERY_VALUE

    suspend fun getImageDetailsByImageID(imageId: String): ImageDetailsTuple =
        appDatabase.imageDao().getImageDetailsByImageId(imageId)

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        private const val DEFAULT_QUERY_VALUE = "Dog"
    }
}
