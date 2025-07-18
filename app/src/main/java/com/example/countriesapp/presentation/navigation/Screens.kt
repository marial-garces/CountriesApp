package com.example.countriesapp.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")

    data object Countries : Screen("countries_screen")

    data object States : Screen("states_screen") {
        fun createRoute(country: String) = "states_screen/$country"
        const val ROUTE_WITH_ARGS = "states_screen/{country}"
    }

    companion object {
        fun getStartDestination() = Splash.route
    }
}
