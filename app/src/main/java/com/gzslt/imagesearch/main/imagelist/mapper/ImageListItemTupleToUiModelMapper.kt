package com.gzslt.imagesearch.main.imagelist.mapper

import com.gzslt.imagesearch.common.util.ImageSize
import com.gzslt.imagesearch.common.util.buildImageUrl
import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel

fun ImageListItemTuple.toUiModel() =
    ImageListItemUiModel(
        imageId = imageId,
        imageUrl = buildImageUrl(
            serverId = serverId,
            imageId = imageId,
            secret = secret,
            size = ImageSize.SMALL,
        ),
    )
