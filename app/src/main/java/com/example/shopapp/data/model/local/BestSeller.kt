package com.example.shopapp.data.model.local

data class BestSeller(
    val id: Int?,
    val isFavorites: Boolean?,
    val title: String?,
    val priceWithoutDiscount: Int?,
    val discountPrice: Int?,
    val picture: String?
)