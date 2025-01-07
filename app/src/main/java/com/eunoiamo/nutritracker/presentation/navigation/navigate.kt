package com.eunoiamo.nutritracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.data.ViewModel.PredictViewModel
import com.eunoiamo.nutritracker.presentation.component.SplashScreen
import com.eunoiamo.nutritracker.presentation.pages.BmiResultScreen
import com.eunoiamo.nutritracker.presentation.pages.HomeScreen
import com.eunoiamo.nutritracker.presentation.pages.InputForm
import com.eunoiamo.nutritracker.presentation.pages.LoginScreen
import com.eunoiamo.nutritracker.presentation.pages.SettingScreen
import com.eunoiamo.nutritracker.presentation.pages.SignUpScreen

@Composable
fun NavbarController(isDarkMode: Boolean, onThemeToggle: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
        composable("homeScreen") { HomeScreen(navController, isDarkMode, onThemeToggle) }
        composable("inputform") { InputForm(navController, isDarkMode, onThemeToggle) }
        composable("settings") { SettingScreen(navController, isDarkMode, onThemeToggle) }
        composable("inputForm") { InputForm(navController, isDarkMode, onThemeToggle) }
        composable("bmiResult") { BmiResultScreen(navController)}
    }
}
