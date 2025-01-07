package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.R
import com.eunoiamo.nutritracker.presentation.component.BottomNavigationBar
import com.eunoiamo.nutritracker.presentation.component.FoodCard
import com.eunoiamo.nutritracker.presentation.component.TopBarMainPage
import com.eunoiamo.nutritracker.ui.theme.blue500

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(12.dp)
            ) {
                item {
                    // Top Icon Section
                    Spacer(modifier = Modifier.height(40.dp))
                    Image(
                        painter = painterResource(id = R.drawable.fitness_icon),
                        contentDescription = "Fitness Icon",
                        modifier = Modifier
                            .size(420.dp)
                            .padding(8.dp)
                    )
                }

                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Selamat Datang di NutriTracker!",
                            style = TextStyle(
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isDarkMode) Color.White else Color(0xFF333333),
                                letterSpacing = 0.5.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Hitung BMI Anda, dapatkan rekomendasi nutrisi harian, dan temukan makanan terbaik untuk gaya hidup sehat.",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = if (isDarkMode) Color.White else Color(0xFF666666),
                                lineHeight = 24.sp,
                                letterSpacing = 0.25.sp
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp, 10.dp)
                        )
                    }
                }

                item {
                    // Start Button Section
                    Button(
                        onClick = {
                            navController.navigate("inputForm") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(blue500)
                    ) {
                        Text(
                            text = "Mulai",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 12.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    )
}

