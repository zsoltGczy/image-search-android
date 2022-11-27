package com.gzslt.imagesearch.common.util

fun buildImageUrl(serverId: String, imageId: String, secret: String, size: ImageSize): String =
    "$BASE_IMAGE_URL/$serverId/${imageId}_${secret}_${size.sizeValue}.jpg"

enum class ImageSize(val sizeValue: String) {
    SMALL("w"),
    LARGE("b"),
}

private const val BASE_IMAGE_URL = "https://live.staticflickr.com"
