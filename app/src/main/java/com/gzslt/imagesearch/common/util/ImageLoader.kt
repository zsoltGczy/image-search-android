package com.gzslt.imagesearch.common.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun loadImageWithCrossFade(context: Context, view: ImageView, imageUrl: String) {
    Glide.with(context)
        .asBitmap()
        .load(imageUrl)
        .apply(RequestOptions.centerCropTransform())
        .transition(BitmapTransitionOptions.withCrossFade(150))
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                transition?.let {
                    val didSucceedTransition = transition.transition(
                        resource,
                        BitmapImageViewTarget(view)
                    )
                    if (!didSucceedTransition)
                        view.setImageBitmap(resource)
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                view.setImageDrawable(placeholder)
            }
        })
}
