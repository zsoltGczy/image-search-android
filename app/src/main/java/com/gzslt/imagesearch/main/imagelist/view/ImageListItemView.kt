package com.gzslt.imagesearch.main.imagelist.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.databinding.ViewImageListItemBinding

class ImageListItemView : FrameLayout {

    private val binding = ViewImageListItemBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        val padding = resources.getDimensionPixelSize(R.dimen.margin_padding_size_medium)
        setPadding(
            padding, padding, padding, padding,
        )
    }
}
