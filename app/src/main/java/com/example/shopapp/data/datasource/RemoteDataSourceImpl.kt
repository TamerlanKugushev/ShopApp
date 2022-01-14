package com.example.shopapp.data.datasource

import com.example.shopapp.data.api.ApiService
import com.example.shopapp.data.model.remote.MainResponse

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getMain(): List<MainResponse> {
        return apiService.getMain().await()
    }
}