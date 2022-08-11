package com.gzslt.imagesearch.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageListApiModel(
    @Json(name = "page")
    val page: Int,
    @Json(name = "pages")
    val pagesTotal: Int,
    @Json(name = "photo")
    val imageList: List<ImageApiModel>,
)
