package com.example.shopapp.data.repository

import com.example.shopapp.data.model.local.Main

interface MainRepository {
    suspend fun getMain(): Main
}