package com.gzslt.imagesearch.common.util

import org.junit.Assert.assertEquals
import org.junit.Test

internal class ImageUrlBuilderUnitTest {

    @Test
    fun `image url is correct`() {
        assertEquals(
            buildImageUrl(
                "65535",
                "52290129723",
                "a8266cb954",
                ImageSize.SMALL
            ),
            "https://live.staticflickr.com/65535/52290129723_a8266cb954_w.jpg"
        )
    }
}
