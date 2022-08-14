package com.gzslt.imagesearch.main.imagelist.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.gzslt.imagesearch.main.imagelist.view.LoadStateFooterItemViewHolder

class ImageLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateFooterItemViewHolder>() {
    override fun onBindViewHolder(
        holder: LoadStateFooterItemViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateFooterItemViewHolder {
        return LoadStateFooterItemViewHolder.create(parent, retry)
    }
}
