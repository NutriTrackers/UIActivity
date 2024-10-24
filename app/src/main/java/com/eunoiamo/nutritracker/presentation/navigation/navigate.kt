package com.eunoiamo.nutritracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.pages.LoginScreen

@Composable
fun NavbarController () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("homeScreen") {
            LoginScreen(navController = navController)
        }
    }
}
