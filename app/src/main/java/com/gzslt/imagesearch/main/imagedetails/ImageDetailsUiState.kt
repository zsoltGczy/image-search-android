package com.gzslt.imagesearch.main.imagedetails

import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel

sealed class ImageDetailsUiState {

    object Loading : ImageDetailsUiState()

    data class Success(val model: ImageDetailsUiModel) : ImageDetailsUiState()

    data class Error(val message: String) : ImageDetailsUiState()
}
