package com.gzslt.imagesearch.main.imagelist.mapper

import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel

fun ImageListItemTuple.toUiModel() =
    ImageListItemUiModel(
        imageId = imageId,
        imageUrl = "$BASE_IMAGE_URL/$serverId/${imageId}_${secret}_$IMAGE_LIST_ITEM_SIZE.jpg"
    )

private const val BASE_IMAGE_URL = "https://live.staticflickr.com"
private const val IMAGE_LIST_ITEM_SIZE = "w"
