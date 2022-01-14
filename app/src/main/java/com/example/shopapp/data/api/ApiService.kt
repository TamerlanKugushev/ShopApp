package com.example.shopapp.data.api

import com.example.shopapp.data.model.remote.MainResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("home")
    fun getMain(): Deferred<List<MainResponse>>

}