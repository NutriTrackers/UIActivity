package com.eunoiamo.nutritracker.presentation.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.component.BottomNavigationBar
import com.eunoiamo.nutritracker.presentation.component.TopBarMainPage
import com.eunoiamo.nutritracker.ui.theme.blue500
import com.eunoiamo.nutritracker.presentation.component.RoundedTextField
import com.eunoiamo.nutritracker.presentation.component.colorComponentField

@Preview
@Composable
private fun inputformprev () {
    val navController = rememberNavController()
    InputForm(
        navController = navController,
        isDarkMode = true,
        onThemeToggle = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InputForm(navController: NavController, isDarkMode: Boolean, onThemeToggle: () -> Unit) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("") }
    var foodRestrictions by remember { mutableStateOf("") }
    var genderOptions = listOf("Laki-laki", "Perempuan")
    var activityLevelOptions = listOf("Sedentary", "Light", "Moderate", "Active", "Very Active")
    var foodRestrictionsOptions = listOf("Dairy", "Gluten", "Nuts", "Seafood", "Eggs")

    var genderExpanded by remember { mutableStateOf(false) }
    var activityLevelExpanded by remember { mutableStateOf(false) }
    var foodRestrictionsExpanded by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

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
        content = {paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )  {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Perhitungan BMI dan Rekomendasi Makanan",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    // Weight Input
                    RoundedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = "Berat badan (kg)",
                        leadingIcon = Icons.Default.Person,
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Height Input
                    RoundedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = "Tinggi badan (cm)",
                        leadingIcon = Icons.Default.Person,
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Age Input
                    RoundedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = "umur",
                        leadingIcon = Icons.Default.Person,
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    //gender input
                    ExposedDropdownMenuBox(
                        expanded = genderExpanded,
                        onExpandedChange = { genderExpanded = !genderExpanded }
                    ) {
                        TextField(
                            value = gender,
                            onValueChange = { gender = it },
                            label = { Text("Jenis Kelamin") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded)
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                            colors = colorComponentField()
                        )
                        ExposedDropdownMenu(
                            expanded = genderExpanded,
                            onDismissRequest = { genderExpanded = false }
                        ) {
                            genderOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        gender = option
                                        genderExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // Activity Level Input
                    ExposedDropdownMenuBox(
                        expanded = activityLevelExpanded,
                        onExpandedChange = { activityLevelExpanded = !activityLevelExpanded }
                    ) {
                        TextField(
                            value = activityLevel,
                            onValueChange = { activityLevel = it },
                            label = { Text("Tingkat Aktivitas") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = activityLevelExpanded)
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                            colors = colorComponentField()
                        )
                        ExposedDropdownMenu(
                            expanded = activityLevelExpanded,
                            onDismissRequest = { activityLevelExpanded = false }
                        ) {
                            activityLevelOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        activityLevel = option
                                        activityLevelExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // Food Restrictions Input
                    ExposedDropdownMenuBox(
                        expanded = foodRestrictionsExpanded,
                        onExpandedChange = { foodRestrictionsExpanded = !foodRestrictionsExpanded }
                    ) {
                        TextField(
                            value = foodRestrictions,
                            onValueChange = { foodRestrictions = it },
                            label = { Text("Pantangan Makanan") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = foodRestrictionsExpanded)
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                            colors = colorComponentField()
                        )
                        ExposedDropdownMenu(
                            expanded = foodRestrictionsExpanded,
                            onDismissRequest = { foodRestrictionsExpanded = false }
                        ) {
                            foodRestrictionsOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        foodRestrictions = option
                                        foodRestrictionsExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    // Submit Button
                    Button(
                        onClick = {
                            // TODO: Handle submit action
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(blue500)
                    ) {
                        Text(
                            text = "Submit",
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


