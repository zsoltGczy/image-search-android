package com.gzslt.imagesearch.main.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gzslt.imagesearch.databinding.FragmentImageDetailsBinding
import com.gzslt.imagesearch.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment() {

    private var _binding: FragmentImageDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ImageDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getImageDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
