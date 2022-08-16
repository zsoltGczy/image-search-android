package com.gzslt.imagesearch.main.imagedetails

import com.gzslt.imagesearch.data.ImageRepository
import com.gzslt.imagesearch.main.imagedetails.mapper.toUiModel
import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel
import javax.inject.Inject

class ImageDetailsPresenter @Inject constructor(
    private val imageRepository: ImageRepository,
) {

    suspend fun getImageDetailsByImageId(imageId: String): ImageDetailsUiModel =
        imageRepository.getImageDetailsByImageID(imageId).toUiModel()
}
