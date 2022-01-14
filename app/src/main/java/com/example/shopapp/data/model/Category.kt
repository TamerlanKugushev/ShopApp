package com.example.shopapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val description: Int,
    var isSelected: Boolean = false
)