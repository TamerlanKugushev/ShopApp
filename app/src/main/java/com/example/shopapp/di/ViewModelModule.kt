package com.example.shopapp.di

import com.example.shopapp.presentation.viewmodel.MainActivityViewModel
import com.example.shopapp.presentation.viewmodel.MainScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { MainScreenViewModel(get()) }
}