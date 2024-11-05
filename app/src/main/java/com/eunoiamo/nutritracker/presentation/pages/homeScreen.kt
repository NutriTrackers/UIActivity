package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.component.BottomNavigationBar
import com.eunoiamo.nutritracker.presentation.component.FoodCard
import com.eunoiamo.nutritracker.presentation.component.TopBarMainPage
@Preview
@Composable
private fun HomePreview() {
    val navController = rememberNavController()

    HomeScreen(
        navController = navController,
        isDarkMode = false,
        onThemeToggle = {}
    )
}

@Composable
fun HomeScreen(navController: NavController, isDarkMode: Boolean, onThemeToggle: () -> Unit) {
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 items per row
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // List of food items with their images and names
                val foodItems = listOf(
                    "https://i.ibb.co.com/Kwm8cDw/image.png" to "Es Dawet",
                    "https://i.ibb.co.com/Kwm8cDw/image.png" to "Mie Ayam",
                    "https://i.ibb.co.com/Kwm8cDw/image.png" to "Nasi Goreng",
                    "https://i.ibb.co.com/Kwm8cDw/image.png" to "Soto Ayam"
                    // Add more food items here
                )

                items(foodItems.size) { index ->
                    val (imageUrl, foodName) = foodItems[index]
                    FoodCard(
                        foodName = foodName,
                        foodImageUrl = imageUrl
                    )
                }
            }
        }
    )
}

