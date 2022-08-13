package com.gzslt.imagesearch.main.imagelist

import androidx.paging.PagingData
import androidx.paging.map
import com.gzslt.imagesearch.data.ImageRepository
import com.gzslt.imagesearch.main.imagelist.mapper.toUiModel
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageListPresenter @Inject constructor(
    private val imageRepository: ImageRepository,
) {

    fun searchImageList(queryString: String): Flow<PagingData<ImageListItemUiModel>> =
        imageRepository.getSearchResultStream(queryString)
            .map { pagingData ->
                pagingData.map { tuple ->
                    tuple.toUiModel()
                }
            }
}
