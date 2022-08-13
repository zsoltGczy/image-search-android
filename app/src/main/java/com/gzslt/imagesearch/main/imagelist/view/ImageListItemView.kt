package com.gzslt.imagesearch.main.imagelist.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gzslt.imagesearch.R
import com.gzslt.imagesearch.common.ui.adapter.BindableBaseRecyclerViewAdapter
import com.gzslt.imagesearch.databinding.ViewImageListItemBinding
import com.gzslt.imagesearch.main.imagelist.adapter.ImageListAdapter
import com.gzslt.imagesearch.main.imagelist.model.ImageListItemUiModel

class ImageListItemView :
    FrameLayout,
    BindableBaseRecyclerViewAdapter.Bindable<ImageListItemUiModel> {

    private val binding = ViewImageListItemBinding.inflate(LayoutInflater.from(context), this)

    var onItemClickListener: ImageListAdapter.OnItemClickListener? = null

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

    override fun bind(model: ImageListItemUiModel) {
        with(binding.imageImageView) {
            Glide.with(context)
                .asBitmap()
                .load(model.imageUrl)
                .transition(BitmapTransitionOptions.withCrossFade())
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        transition?.let {
                            val didSucceedTransition = transition.transition(
                                resource,
                                BitmapImageViewTarget(binding.imageImageView)
                            )
                            if (!didSucceedTransition)
                                setImageBitmap(resource)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        setImageDrawable(placeholder)
                    }
                })

            setOnClickListener {
                onItemClickListener?.onItemClicked(model.imageId)
            }
        }
    }
}
