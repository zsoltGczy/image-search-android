package com.gzslt.imagesearch.data.api

import com.gzslt.imagesearch.data.api.model.ResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("?method=flickr.photos.search")
    suspend fun getByText(
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
    ): ResponseApiModel
}
