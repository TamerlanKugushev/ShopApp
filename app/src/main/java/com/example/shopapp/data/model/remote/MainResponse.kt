package com.example.shopapp.data.model.remote

import com.example.shopapp.data.model.remote.BestSellerResponse
import com.example.shopapp.data.model.remote.HomeStoreResponse
import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("home_store") val homeStore: List<HomeStoreResponse>,
    @SerializedName("best_seller") val bestSeller: List<BestSellerResponse>
)