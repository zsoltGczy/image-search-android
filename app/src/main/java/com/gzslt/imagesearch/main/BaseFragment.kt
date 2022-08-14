package com.gzslt.imagesearch.main

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected fun getMainActivity() = activity as MainActivity
}
