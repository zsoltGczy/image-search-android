package com.gzslt.imagesearch.main.imagedetails.mapper

import com.gzslt.imagesearch.common.util.ImageSize
import com.gzslt.imagesearch.common.util.buildImageUrl
import com.gzslt.imagesearch.data.db.tuple.ImageDetailsTuple
import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel
import java.text.ParseException
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
        title = title.orEmpty(),
        tags = tags.orEmpty().split(DELIMITER),
        description = description.orEmpty(),
        ownerName = ownerName.orEmpty(),
        date = getDateString(date.orEmpty()),
    )

private fun getDateString(date: String): String =
    try {
        SimpleDateFormat(DATE_PATTERN, Locale.US)
            .parse(date)?.toString() ?: DATE_IS_MISSING
    } catch (exception: ParseException) {
        DATE_IS_MISSING
    }

private const val DELIMITER = " "
private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_IS_MISSING = "-"
