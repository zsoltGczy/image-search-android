package com.gzslt.imagesearch.main.imagedetails.mapper

import com.gzslt.imagesearch.common.util.ImageSize
import com.gzslt.imagesearch.common.util.buildImageUrl
import com.gzslt.imagesearch.data.db.tuple.ImageDetailsTuple
import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel
import java.text.SimpleDateFormat
import java.util.Locale

fun ImageDetailsTuple.toUiModel() =
    ImageDetailsUiModel(
        id = id,
        imageUrl = buildImageUrl(
            serverId = serverId,
            imageId = imageId,
            secret = secret,
            size = ImageSize.LARGE
        ),
        title = title,
        tags = if (EMPTY_TAGS != tags) tags.split(DELIMITER) else emptyList(),
        description = description,
        ownerName = ownerName,
        date = getDateString(date),
    )

private fun getDateString(date: String): String =
    SimpleDateFormat(DATE_PATTERN, Locale.US)
        .parse(date)
        ?.toString()
        ?: DATE_IS_MISSING

private const val EMPTY_TAGS = ""
private const val DELIMITER = " "
private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_IS_MISSING = "-"
