package com.gzslt.imagesearch.main.imagedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    fun getImageDetails() {
        savedStateHandle.get<String>(IMAGE_ID_NAV_ARG_KEY)?.let { imageId ->
            // TODO get imageDetailsUiModel from database
            logcat { imageId }
        }
    }

    companion object {
        private const val IMAGE_ID_NAV_ARG_KEY = "imageId"
    }
}
