package com.example.shopapp.data.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader {
    override fun loadImage(uri: String, view: ImageView) {
        Glide.with(view).load(uri).into(view)
    }
}