package com.gzslt.imagesearch.main.navigation

import android.content.Context
import com.gzslt.imagesearch.common.navigation.BaseNavigator
import com.gzslt.imagesearch.main.MainActivity
import com.gzslt.imagesearch.main.imagelist.ImageListFragmentDirections
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MainNavigator @Inject constructor(@ActivityContext context: Context) :
    BaseNavigator(context as MainActivity) {

    fun navigateToDetails(imageId: String) {
        navigate(
            ImageListFragmentDirections
                .actionImageListFragmentToImageDetailsFragment(imageId)
        )
    }
}
