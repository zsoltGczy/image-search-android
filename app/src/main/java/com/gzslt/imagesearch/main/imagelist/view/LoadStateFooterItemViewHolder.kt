package com.gzslt.imagesearch.main.imagelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.databinding.ViewLoadStateFooterItemBinding

class LoadStateFooterItemViewHolder(
    private val binding: ViewLoadStateFooterItemBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        with(binding) {
            if (loadState is LoadState.Error) {
                errorMessageTextView.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMessageTextView.isVisible = loadState is LoadState.Error
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateFooterItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_load_state_footer_item, parent, false)
            val binding = ViewLoadStateFooterItemBinding.bind(view)
            return LoadStateFooterItemViewHolder(binding, retry)
        }
    }
}
