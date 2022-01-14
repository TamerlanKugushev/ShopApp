package com.example.shopapp.data.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(
        uri: String,
        view: ImageView,
    )
}