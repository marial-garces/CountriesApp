package com.example.countriesapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.presentation.controller.countries.CountriesScreen
import com.example.countriesapp.presentation.controller.splash.SplashScreenController
import com.example.countriesapp.presentation.controller.states.StateScreenController
import com.example.countriesapp.presentation.navigation.Screen.Companion.getStartDestination

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = getStartDestination(),

    ){
        composable(route = Screen.Splash.route) {
            SplashScreenController(navController = navController)
        }
        composable(route = Screen.Countries.route) {
            CountriesScreen(navController = navController)
        }
        composable(route = Screen.States.route) {
            StateScreenController(navController = navController)
        }
    }
}



