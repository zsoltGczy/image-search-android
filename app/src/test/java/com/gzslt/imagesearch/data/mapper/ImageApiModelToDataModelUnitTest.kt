package com.gzslt.imagesearch.data.mapper

import com.gzslt.imagesearch.data.api.model.ImageApiModel
import com.gzslt.imagesearch.data.db.model.ImageDataModel
import org.junit.Assert.assertEquals
import org.junit.Test

internal class ImageApiModelToDataModelUnitTest {

    @Test
    fun `when api model contains null value it should be empty string`() {
        val apiModel = ImageApiModel(
            id = "imageId12354",
            secret = "secret123",
            serverId = "serverId2345",
            title = null,
            descriptionApiModel = null,
            date = null,
            ownerName = null,
            tags = null,
        )

        assertEquals(
            ImageDataModel(
                id = 0,
                imageId = "imageId12354",
                secret = "secret123",
                serverId = "serverId2345",
                title = "",
                description = "",
                date = "",
                ownerName = "",
                tags = "",
            ),
            apiModel.toDataModel()
        )
    }
}
