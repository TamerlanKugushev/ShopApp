package com.example.shopapp.domain.usecases

import com.example.shopapp.data.model.local.Main

interface MainScreenUseCase {
    suspend fun getMain(): Main
}