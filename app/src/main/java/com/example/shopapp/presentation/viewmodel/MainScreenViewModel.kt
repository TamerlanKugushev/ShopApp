package com.example.shopapp.presentation.viewmodel

import com.example.shopapp.domain.util.Resource
import com.example.shopapp.core.viewmodel.BaseViewModel
import com.example.shopapp.data.model.local.Main
import com.example.shopapp.domain.usecases.MainScreenUseCase

class MainScreenViewModel(private val mainScreenUseCase: MainScreenUseCase) : BaseViewModel<Main>() {

    fun getMain() {
        runAsync {
            showLoading()
            _liveData.postValue(Resource.Success(mainScreenUseCase.getMain()))
        }
    }

    fun toProductDetailsScreen(id: String){
        //navigator.toProductDetailsScreen(id)
    }

}