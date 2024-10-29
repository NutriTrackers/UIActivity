package com.eunoiamo.nutritracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.component.SplashScreen
import com.eunoiamo.nutritracker.presentation.pages.HomeScreen
import com.eunoiamo.nutritracker.presentation.pages.LoginScreen
import com.eunoiamo.nutritracker.presentation.pages.ProfileScreen
import com.eunoiamo.nutritracker.presentation.pages.SettingScreen
import com.eunoiamo.nutritracker.presentation.pages.SignUpScreen

@Composable
fun NavbarController () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
        composable("homeScreen") { HomeScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("settings") { SettingScreen(navController) }
    }
}
