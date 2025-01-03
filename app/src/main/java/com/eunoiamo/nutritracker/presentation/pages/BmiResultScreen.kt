package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    val foodData = listOf(
        "Cereals ready-to-eat" to "Protein: 2.3g, Carb: 47.5g, Fat: 0.5g",
        "Pinon Nuts, roasted" to "Protein: 1.1g, Carb: 43.4g, Fat: 1.4g",
        "Spices, oregano, dried" to "Protein: 1.7g, Carb: 42.5g, Fat: 0.3g",
        "Spices, coriander seed" to "Protein: 1.2g, Carb: 41.9g, Fat: 0.4g",
        "Spices, marjoram, dried" to "Protein: 1.4g, Carb: 40.3g, Fat: 0.2g"
    )
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
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "BMI Results",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

                // BMI Information Section
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCF0E4)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("BMI: 24.22", fontSize = 16.sp)
                        Text("Category: Normal", fontSize = 16.sp)
                        Text("Daily Calories: 2006 kcal", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Nutritional Details:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Protein: 56 g", fontSize = 14.sp)
                        Text("Fat: 56 g", fontSize = 14.sp)
                        Text("Carbohydrate: 276 g", fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Food Recommendations Section
                Text(
                    text = "Food Recommendations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                foodData.forEachIndexed { index, food ->
                    FoodItemCard(
                        foodName = food.first,
                        details = food.second,
                        isExpanded = expandedIndex == index,
                        onClick = {
                            expandedIndex = if (expandedIndex == index) -1 else index
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
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
