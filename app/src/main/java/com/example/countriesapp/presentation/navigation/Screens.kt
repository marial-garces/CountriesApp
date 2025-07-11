package com.example.countriesapp.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")

    data object Countries : Screen("countries_screen")

    data object States : Screen("states_screen")

    companion object {
        fun getStartDestination() = Splash.route
    }
}