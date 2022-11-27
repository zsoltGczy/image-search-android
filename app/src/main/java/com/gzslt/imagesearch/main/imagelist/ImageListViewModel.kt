package com.gzslt.imagesearch.main.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gzslt.imagesearch.data.ImageRepository
import com.gzslt.imagesearch.main.imagelist.mapper.toUiModel
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {
    val state: StateFlow<String>
    val pagingDataFlow: Flow<PagingData<ImageListItemUiModel>>
    private val searchFlow = MutableSharedFlow<String>()

    init {
        val initialQuery: String = imageRepository.getSavedQueryOrDefault()
        val searches = searchFlow
            .distinctUntilChanged()
            .onStart { emit(initialQuery) }

        state = searches
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = initialQuery
            )

        pagingDataFlow = searches
            .flatMapLatest(this::searchImageList)
            .cachedIn(viewModelScope)
    }

    private fun searchImageList(queryString: String): Flow<PagingData<ImageListItemUiModel>> =
        imageRepository.getSearchResultStream(queryString)
            .map { pagingData ->
                pagingData.map { tuple ->
                    tuple.toUiModel()
                }
            }

    fun searchEvent(query: String) {
        viewModelScope.launch { searchFlow.emit(query) }
    }
}
