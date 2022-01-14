package com.example.shopapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopapp.navigation.MainNavigator

class MainActivityViewModel(private val navigator: MainNavigator) : ViewModel() {

    fun toMainScreen(){
        navigator.toMainScreen()
    }

}