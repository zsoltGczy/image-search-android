package com.gzslt.imagesearch.main.imagelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel

object ImageDiffUtil : DiffUtil.ItemCallback<ImageListItemUiModel>() {
    override fun areItemsTheSame(
        oldItem: ImageListItemUiModel,
        newItem: ImageListItemUiModel,
    ): Boolean =
        oldItem.imageId == newItem.imageId

    override fun areContentsTheSame(
        oldItem: ImageListItemUiModel,
        newItem: ImageListItemUiModel,
    ): Boolean =
        oldItem == newItem
}
