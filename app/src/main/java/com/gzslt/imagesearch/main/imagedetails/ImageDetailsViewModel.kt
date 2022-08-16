package com.gzslt.imagesearch.main.imagedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val presenter: ImageDetailsPresenter,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ImageDetailsUiState>(ImageDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getImageDetails()
    }

    fun getImageDetails() {
        savedStateHandle.get<String>(IMAGE_ID_NAV_ARG_KEY)?.let { imageId ->
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.emit(ImageDetailsUiState.Loading)
                try {
                    _uiState.emit(
                        ImageDetailsUiState.Success(
                            presenter.getImageDetailsByImageId(imageId)
                        )
                    )
                } catch (exception: Exception) {
                    _uiState.emit(ImageDetailsUiState.Error(exception.message.toString()))
                }
            }
        }
    }

    companion object {
        private const val IMAGE_ID_NAV_ARG_KEY = "imageId"
    }
}
