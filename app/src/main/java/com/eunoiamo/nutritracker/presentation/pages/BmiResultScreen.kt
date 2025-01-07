package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.data.ViewModel.PredictViewModel
import com.eunoiamo.nutritracker.data.api.ApiClient
import com.eunoiamo.nutritracker.ui.theme.blue500

@Preview
@Composable
private fun BmiResultScreenPreview() {
    val navController = rememberNavController()
    BmiResultScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BmiResultScreen(navController: NavHostController) {
    // Mengambil hasil prediksi yang sudah disimpan
    val predictionResult = ApiClient.getPredictionResult()

    val scrollState = rememberScrollState()
    var expandedIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Result") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "BMI Results",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(30.dp))

                // Mengecek apakah hasil prediksi ada
                predictionResult?.let { result ->
                    // BMI Information Section
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFDCF0E4),
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 7.dp, shape = RoundedCornerShape(16.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("BMI: ${result.bmi}", fontSize = 16.sp)
                            Text("Category: ${result.statusLabel}", fontSize = 16.sp)
                            Text("Daily Calories: ${result.caloriesNeeded} kcal", fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Nutritional Details:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Protein: ${result.bmr} g", fontSize = 14.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Food Recommendations Section
                    Text(
                        text = "Food & Drink Recommendations",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Daftar makanan berdasarkan data prediksi
                    result.recommendedFoods.forEachIndexed { index, food ->
                        FoodItemCard(
                            foodName = food.food,
                            details = "Calories: ${food.calories} kcal, Protein: ${food.protein} g, Fat: ${food.fat} g, Carbs: ${food.carbs} g, Category: ${food.category}",
                            isExpanded = expandedIndex == index,
                            onClick = {
                                expandedIndex = if (expandedIndex == index) -1 else index
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}


@Composable
fun FoodItemCard(
    foodName: String,
    details: String,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription = "Food Icon",
                    tint = blue500,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = foodName,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                    )
                }
            }
            if (isExpanded) {
                Text(
                    text = details,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


