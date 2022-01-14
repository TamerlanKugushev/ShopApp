package com.example.shopapp.di

import com.example.shopapp.navigation.MainNavigator
import com.example.shopapp.navigation.MainNavigatorImpl
import com.example.shopapp.navigation.Screens
import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module

val ciceroneModule = module {

    val cicerone = Cicerone.create()

    factory { cicerone.router }

    factory { cicerone.getNavigatorHolder() }

    single { Screens }
}


val navigationModule = module {

    factory<MainNavigator> { MainNavigatorImpl(get(), get()) }
}