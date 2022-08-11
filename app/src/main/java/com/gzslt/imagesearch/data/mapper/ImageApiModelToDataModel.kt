package com.gzslt.imagesearch.data.mapper

import com.gzslt.imagesearch.data.api.model.ImageApiModel
import com.gzslt.imagesearch.data.db.model.ImageDataModel

fun ImageApiModel.toDataModel() =
    ImageDataModel(
        imageId = id,
        secret = secret,
        serverId = serverId,
        title = title ?: "",
        description = descriptionApiModel?.content ?: "",
        date = date ?: "",
        ownerName = ownerName ?: "",
        tags = tags ?: "",
    )
