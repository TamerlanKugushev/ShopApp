package com.example.shopapp.di

import com.example.shopapp.data.api.ApiService
import com.example.shopapp.data.api.RetrofitBuilder
import org.koin.dsl.module

val retrofitModule = module {
    single { RetrofitBuilder.create(ApiService::class.java) }
}