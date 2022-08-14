package com.gzslt.imagesearch.common.ui.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.gzslt.imagesearch.common.ui.view.ViewHolder

abstract class BindableBaseRecyclerViewAdapter<T, V>(diffUtil: DiffUtil.ItemCallback<T>) :
    RecyclerViewAdapterBase<T, V>(diffUtil)
        where T : Any,
              V : View,
              V : BindableBaseRecyclerViewAdapter.Bindable<T> {

    override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
        getItem(position)?.let {
            holder.view.bind(it)
        }
    }

    interface Bindable<T> {
        fun bind(model: T)
    }
}
