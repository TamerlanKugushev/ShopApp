package com.example.shopapp.data.datasource

import com.example.shopapp.data.model.remote.MainResponse

interface RemoteDataSource {
    suspend fun getMain(): List<MainResponse>
}