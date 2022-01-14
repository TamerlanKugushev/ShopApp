package com.example.shopapp.data.model.local

import com.example.shopapp.data.model.ILocalData

data class Main(
    val id: String?,
    val homeStore: List<HomeStore>?,
    val bestSeller: List<BestSeller>?
) : ILocalData