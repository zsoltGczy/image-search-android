package com.gzslt.imagesearch.data.db.tuple

data class ImageDetailsTuple(
    val id: Long,
    val imageId: String,
    val secret: String,
    val serverId: String,
    val title: String?,
    val description: String?,
    val date: String?,
    val ownerName: String?,
    val tags: String?,
)
