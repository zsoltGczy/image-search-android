package com.gzslt.imagesearch.common.navigation

import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.main.MainActivity

abstract class BaseNavigator(mainActivity: MainActivity) {

    private val navController by lazy {
        Navigation.findNavController(mainActivity, R.id.fragmentNavHost)
    }

    protected fun navigate(direction: NavDirections) {
        navController.navigate(direction)
    }
}
