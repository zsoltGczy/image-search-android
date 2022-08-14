package com.gzslt.imagesearch.main.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val presenter: ImageListPresenter,
) : ViewModel() {
    val state: StateFlow<String>
    val pagingDataFlow: Flow<PagingData<ImageListItemUiModel>>
    val searchEvent: (String) -> Unit

    init {
        val initialQuery: String = presenter.getSavedQueryOrDefault()
        val searchFlow = MutableSharedFlow<String>()
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
            .flatMapLatest(presenter::searchImageList)
            .cachedIn(viewModelScope)

        searchEvent = { query ->
            viewModelScope.launch { searchFlow.emit(query) }
        }
    }
}
