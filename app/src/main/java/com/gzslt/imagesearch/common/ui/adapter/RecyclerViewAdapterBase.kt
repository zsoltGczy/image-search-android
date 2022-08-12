package com.gzslt.imagesearch.common.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gzslt.imagesearch.common.ui.view.ViewHolder

abstract class RecyclerViewAdapterBase<T : Any, V : View>(diffUtil: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, ViewHolder<V>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<V> =
        ViewHolder(onCreateItemView(parent, viewType))

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V
}
