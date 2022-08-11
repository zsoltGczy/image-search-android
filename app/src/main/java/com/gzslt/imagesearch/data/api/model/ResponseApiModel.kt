package com.gzslt.imagesearch.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseApiModel(
    @Json(name = "photos")
    val imageListApiModel: ImageListApiModel,
)
