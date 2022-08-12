package com.gzslt.imagesearch.main.imagelist.adapter

import android.content.Context
import android.view.ViewGroup
import com.gzslt.imagesearch.common.ui.adapter.BindableBaseRecyclerViewAdapter
import com.gzslt.imagesearch.common.ui.view.ViewHolder
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel
import com.gzslt.imagesearch.main.imagelist.view.ImageListItemView

class ImageListAdapter(private val context: Context) :
    BindableBaseRecyclerViewAdapter<ImageListItemUiModel, ImageListItemView>(ImageDiffUtil) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ImageListItemView =
        ImageListItemView(context)

    override fun onBindViewHolder(holder: ViewHolder<ImageListItemView>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.view.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(imageId: String)
    }
}
