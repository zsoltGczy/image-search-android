package com.gzslt.imagesearch.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageApiModel(
    @Json(name = "id")
    val id: String,
    @Json(name = "secret")
    val secret: String,
    @Json(name = "server")
    val serverId: String,
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val descriptionApiModel: ImageDescriptionApiModel?,
    @Json(name = "datetaken")
    val date: String?,
    @Json(name = "ownername")
    val ownerName: String?,
    @Json(name = "tags")
    val tags: String?,
)
