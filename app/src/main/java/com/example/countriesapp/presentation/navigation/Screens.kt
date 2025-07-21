package com.example.countriesapp.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")

    data object Countries : Screen("countries_screen")

    data object States : Screen("states_screen") {
        fun createRoute(country: String) = "states_screen/$country"
        const val ROUTE_WITH_ARGS = "states_screen/{country}"
    }

    data object Population : Screen("population_screen") {
        fun createRoute(city: String) = "population_screen/$city"
        const val ROUTE_WITH_ARGS = "population_screen/{city}"
    }

    companion object {
        fun getStartDestination() = Splash.route
    }
}
