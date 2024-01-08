package com.example.androidlab6jc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidlab6jc.ui.screens.CitiesScreen
import com.example.androidlab6jc.ui.screens.MainScreen
import com.example.androidlab6jc.ui.screens.Routes

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN,
    ) {
        composable(
            Routes.MAIN_SCREEN
        ) {
            MainScreen()
        }

        composable(
            Routes.CITIES_SCREEN
        ) {
            CitiesScreen()
        }

    }
}