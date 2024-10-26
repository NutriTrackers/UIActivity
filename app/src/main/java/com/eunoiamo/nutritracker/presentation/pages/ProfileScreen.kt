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
import com.eunoiamo.nutritracker.presentation.component.TopBarWithBackButton

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ProfileScreen (navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(
                navController = navController,
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 120.dp),
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "ProfileScreen",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 140.dp),
                    color = Color.Black,
                )
            }
        }
    )
}