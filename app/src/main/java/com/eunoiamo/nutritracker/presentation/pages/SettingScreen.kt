package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eunoiamo.nutritracker.presentation.component.BottomNavigationBar
import com.eunoiamo.nutritracker.presentation.component.TopBarMainPage
import com.eunoiamo.nutritracker.presentation.component.TopBarWithBackButton


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController, isDarkMode: Boolean, onThemeToggle: () -> Unit) {
    Scaffold(
        topBar = {
            TopBarMainPage(
                navController = navController,
                isDarkMode = isDarkMode,
                onThemeToggle = onThemeToggle
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                isDarkMode = isDarkMode
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Setting Screen",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkMode) Color.White else Color.Black,
                )
            }
        }
    )
}
