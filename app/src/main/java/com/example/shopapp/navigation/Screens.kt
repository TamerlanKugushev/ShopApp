package com.example.shopapp.navigation

import com.example.shopapp.presentation.view.fragments.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun mainScreen() = FragmentScreen { MainFragment() }
}