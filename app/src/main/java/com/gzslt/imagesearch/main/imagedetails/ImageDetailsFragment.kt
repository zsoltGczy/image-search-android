package com.gzslt.imagesearch.main.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.common.util.loadImageWithCrossFade
import com.gzslt.imagesearch.databinding.FragmentImageDetailsBinding
import com.gzslt.imagesearch.main.BaseFragment
import com.gzslt.imagesearch.main.imagedetails.model.ImageDetailsUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                when (val state = uiState) {
                    ImageDetailsUiState.Loading -> {
                        setContentVisibilityByUiState(
                            isLoading = true,
                            isSuccess = false,
                            isError = false,
                        )
                    }
                    is ImageDetailsUiState.Success -> {
                        setContentVisibilityByUiState(
                            isLoading = false,
                            isSuccess = true,
                            isError = false,
                        )

                        setSuccessContent(state.model)
                    }
                    is ImageDetailsUiState.Error -> {
                        setContentVisibilityByUiState(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                        )

                        setErrorContent(state.message)
                    }
                }
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.getImageDetails()
        }
    }

    private fun setSuccessContent(model: ImageDetailsUiModel) {
        with(binding) {
            loadImageWithCrossFade(
                getMainActivity(),
                imagePhotoView,
                model.imageUrl,
            )
            titleTextView.text = model.title
            model.tags.let { tags ->
                tagHorizontalScrollView.isVisible = tags.isNotEmpty()
                if (tagHorizontalScrollView.isVisible) {
                    tags.forEach { tagText ->
                        tagChipGroup.addView(
                            Chip(getMainActivity()).apply {
                                text = tagText
                            }
                        )
                    }
                }
            }
            descriptionTextView.text = model.description
            ownerNameTextView.text = model.ownerName
            dateTextView.text = model.date
        }
    }

    private fun setErrorContent(message: String) {
        binding.errorMessageTextView.text =
            getString(R.string.image_details_error_error_text, message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setContentVisibilityByUiState(
        isLoading: Boolean,
        isSuccess: Boolean,
        isError: Boolean,
    ) {
        with(binding) {
            progressBar.isVisible = isLoading
            successConstraintLayout.isVisible = isSuccess
            errorLinearLayout.isVisible = isError
        }
    }
}
