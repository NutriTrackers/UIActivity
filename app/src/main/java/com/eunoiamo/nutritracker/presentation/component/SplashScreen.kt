package com.eunoiamo.nutritracker.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.R
import com.eunoiamo.nutritracker.presentation.pages.LoginScreen
import com.eunoiamo.nutritracker.ui.theme.gray200
import com.eunoiamo.nutritracker.ui.theme.gray300
import com.eunoiamo.nutritracker.ui.theme.gray500
import kotlinx.coroutines.delay

@Preview
@Composable
fun prevSplashScreen(){
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
@Composable
fun SplashScreen(navController: NavController) {
    val splashDuration = 2000L
    LaunchedEffect(key1 = true) {
        delay(splashDuration)
        navController.navigate("homeScreen") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gray200),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_no_background),
            contentDescription = "logo no background",
            modifier = Modifier.size(180.dp)
        )
    }
}
