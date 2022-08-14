package com.gzslt.imagesearch.main.imagelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.databinding.FragmentImageListBinding
import com.gzslt.imagesearch.main.BaseFragment
import com.gzslt.imagesearch.main.imagelist.adapter.ImageListAdapter
import com.gzslt.imagesearch.main.imagelist.adapter.ImageLoadStateAdapter
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageListFragment : BaseFragment() {

    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ImageListViewModel by viewModels()

    private lateinit var imageListAdapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi(
            uiState = viewModel.state,
            pagingData = viewModel.pagingDataFlow,
            onQueryChanged = viewModel.searchEvent
        )
    }

    private fun initUi(
        uiState: StateFlow<String>,
        pagingData: Flow<PagingData<ImageListItemUiModel>>,
        onQueryChanged: (String) -> Unit,
    ) {
        imageListAdapter = ImageListAdapter(getMainActivity()).apply {
            onItemClickListener = object : ImageListAdapter.OnItemClickListener {
                override fun onItemClicked(imageId: String) {
                    // TODO navigate to details screen
                }
            }
        }

        val footer = ImageLoadStateAdapter { imageListAdapter.retry() }
        val adapterWithFooter = imageListAdapter.withLoadStateFooter(
            footer = footer
        )

        with(binding.imageListRecyclerView) {
            layoutManager = GridLayoutManager(getMainActivity(), 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        if (imageListAdapter.getItemViewType(position) == imageListAdapter.ERROR_VIEW_TYPE)
                            1
                        else
                            2
                }
            }
            adapter = adapterWithFooter
        }

        bindSearch(
            uiState = uiState,
            onQueryChanged = onQueryChanged,
        )

        bindList(
            pagingData = pagingData,
            footer = footer,
        )
    }

    private fun bindSearch(
        uiState: StateFlow<String>,
        onQueryChanged: (String) -> Unit
    ) {
        with(binding.searchTextInputEditText) {
            setOnEditorActionListener { _, actionId, _ ->
                if (EditorInfo.IME_ACTION_SEARCH == actionId) {
                    updateImageListFromInput(onQueryChanged)
                    hideKeyboard()
                }
                false
            }

            lifecycleScope.launch {
                uiState.collect(::setText)
            }
        }
    }

    private fun updateImageListFromInput(onQueryChanged: (String) -> Unit) {
        with(binding) {
            searchTextInputEditText.text.trim().let {
                if (it.isNotEmpty()) {
                    imageListRecyclerView.scrollToPosition(0)
                    onQueryChanged(it.toString())
                }
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getMainActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(
            binding.searchTextInputEditText.windowToken,
            0
        )
    }

    private fun bindList(
        footer: ImageLoadStateAdapter,
        pagingData: Flow<PagingData<ImageListItemUiModel>>,
    ) {
        lifecycleScope.launch {
            pagingData.collectLatest(imageListAdapter::submitData)
        }

        lifecycleScope.launch {
            imageListAdapter.loadStateFlow.collect { loadState ->
                footer.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && imageListAdapter.itemCount > 0 }
                    ?: loadState.append

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    Toast.makeText(
                        getMainActivity(),
                        getString(R.string.image_list_error_load_state_error, it.error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
