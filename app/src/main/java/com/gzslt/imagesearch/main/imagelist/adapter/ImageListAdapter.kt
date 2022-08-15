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

    val IMAGE_VIEW_TYPE = 0
    val ERROR_VIEW_TYPE = 1

    override fun getItemViewType(position: Int): Int =
        if (position == itemCount) {
            IMAGE_VIEW_TYPE
        } else {
            ERROR_VIEW_TYPE
        }

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
