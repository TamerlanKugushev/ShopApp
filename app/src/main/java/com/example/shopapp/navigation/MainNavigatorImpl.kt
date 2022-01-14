package com.example.shopapp.navigation

import com.github.terrakok.cicerone.Router

class MainNavigatorImpl(private val screens: Screens, private val router: Router) : MainNavigator {
    override fun toMainScreen() {
        router.replaceScreen(Screens.mainScreen())
    }

    override fun onBackPressed() {
        router.exit()
    }
}