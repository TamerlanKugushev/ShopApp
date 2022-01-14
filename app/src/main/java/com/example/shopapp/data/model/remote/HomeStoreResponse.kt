package com.example.shopapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class HomeStoreResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("picture") val picture: String
)