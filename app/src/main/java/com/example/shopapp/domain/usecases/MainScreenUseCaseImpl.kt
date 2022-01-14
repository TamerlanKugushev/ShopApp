package com.example.shopapp.domain.usecases

import com.example.shopapp.data.repository.MainRepository
import com.example.shopapp.data.model.local.Main
import com.example.shopapp.domain.usecases.MainScreenUseCase

class MainScreenUseCaseImpl(private val mainRepository: MainRepository) : MainScreenUseCase {

    override suspend fun getMain(): Main {
        return mainRepository.getMain()
    }
}