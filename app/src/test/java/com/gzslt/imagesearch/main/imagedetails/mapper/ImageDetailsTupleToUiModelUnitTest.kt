package com.gzslt.imagesearch.main.imagedetails.mapper

import com.gzslt.imagesearch.data.db.tuple.ImageDetailsTuple
import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

internal class ImageDetailsTupleToUiModelUnitTest {

    @Test
    fun `mapping with every property added`() {
        assertEquals(
            ImageDetailsTuple(
                id = 1,
                imageId = "52526909560",
                secret = "397b2a5c88",
                serverId = "65535",
                description = "",
                title = "German Shepherd Meet-ups Hoddesdon",
                date = "2022-02-27 09:35:39",
                ownerName = "motorsportimagesbyghp",
                tags = "germanshepherd germanshepherddog hoddesdon meetup walkies walk",
            ).toUiModel(),
            ImageDetailsUiModel(
                id = 1,
                imageUrl = "https://live.staticflickr.com/65535/52526909560_397b2a5c88_b.jpg",
                title = "German Shepherd Meet-ups Hoddesdon",
                tags = listOf(
                    "germanshepherd",
                    "germanshepherddog",
                    "hoddesdon",
                    "meetup",
                    "walkies",
                    "walk",
                ),
                description = "",
                ownerName = "motorsportimagesbyghp",
                date = "2022-02-27",
            )
        )
    }

    @Test
    fun `mapping with null properties`() {
        assertEquals(
            ImageDetailsTuple(
                id = 1,
                imageId = "52526909560",
                secret = "397b2a5c88",
                serverId = "65535",
                description = "",
                title = null,
                date = "2022-02-27 09:35:39",
                ownerName = null,
                tags = null,
            ).toUiModel(),
            ImageDetailsUiModel(
                id = 1,
                imageUrl = "https://live.staticflickr.com/65535/52526909560_397b2a5c88_b.jpg",
                title = "",
                tags = emptyList(),
                description = "",
                ownerName = "",
                date = "2022-02-27",
            )
        )
    }

    @Test
    fun `mapping with empty tag string`() {
        assertEquals(
            ImageDetailsTuple(
                id = 1,
                imageId = "52526909560",
                secret = "397b2a5c88",
                serverId = "65535",
                description = "",
                title = "German Shepherd Meet-ups Hoddesdon",
                date = "2022-02-27 09:35:39",
                ownerName = "motorsportimagesbyghp",
                tags = "",
            ).toUiModel(),
            ImageDetailsUiModel(
                id = 1,
                imageUrl = "https://live.staticflickr.com/65535/52526909560_397b2a5c88_b.jpg",
                title = "German Shepherd Meet-ups Hoddesdon",
                tags = emptyList(),
                description = "",
                ownerName = "motorsportimagesbyghp",
                date = "2022-02-27",
            )
        )
    }

    @Test
    fun `mapping with wrong date string`() {
        assertEquals(
            ImageDetailsTuple(
                id = 1,
                imageId = "52526909560",
                secret = "397b2a5c88",
                serverId = "65535",
                description = "",
                title = "German Shepherd Meet-ups Hoddesdon",
                date = "wrong date",
                ownerName = "motorsportimagesbyghp",
                tags = "germanshepherd germanshepherddog hoddesdon meetup walkies walk",
            ).toUiModel(),
            ImageDetailsUiModel(
                id = 1,
                imageUrl = "https://live.staticflickr.com/65535/52526909560_397b2a5c88_b.jpg",
                title = "German Shepherd Meet-ups Hoddesdon",
                tags = listOf(
                    "germanshepherd",
                    "germanshepherddog",
                    "hoddesdon",
                    "meetup",
                    "walkies",
                    "walk",
                ),
                description = "",
                ownerName = "motorsportimagesbyghp",
                date = "-",
            )
        )
    }

    @Test
    fun `mapping with null date string`() {
        assertEquals(
            ImageDetailsTuple(
                id = 1,
                imageId = "52526909560",
                secret = "397b2a5c88",
                serverId = "65535",
                description = "",
                title = "German Shepherd Meet-ups Hoddesdon",
                date = null,
                ownerName = "motorsportimagesbyghp",
                tags = "germanshepherd germanshepherddog hoddesdon meetup walkies walk",
            ).toUiModel(),
            ImageDetailsUiModel(
                id = 1,
                imageUrl = "https://live.staticflickr.com/65535/52526909560_397b2a5c88_b.jpg",
                title = "German Shepherd Meet-ups Hoddesdon",
                tags = listOf(
                    "germanshepherd",
                    "germanshepherddog",
                    "hoddesdon",
                    "meetup",
                    "walkies",
                    "walk",
                ),
                description = "",
                ownerName = "motorsportimagesbyghp",
                date = "-",
            )
        )
    }
}
