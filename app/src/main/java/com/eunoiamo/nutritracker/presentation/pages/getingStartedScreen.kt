package com.eunoiamo.nutritracker.presentation.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.eunoiamo.nutritracker.R
import com.eunoiamo.nutritracker.ui.theme.blue500

class GettingStartedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GettingStartedScreen(
                onStartClick = {
                    // TODO: Handle navigation to the next screen (e.g., MainActivity)
                }
            )
        }
    }
}

@Composable
fun GettingStartedScreen(onStartClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Icon Section
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.fitness_icon),
                contentDescription = "Fitness Icon",
                modifier = Modifier
                    .size(520.dp)
                    .padding(8.dp)
            )

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
                        color = Color(0xFF333333),
                        letterSpacing = 0.5.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hitung BMI Anda, dapatkan rekomendasi nutrisi harian, dan temukan makanan terbaik untuk gaya hidup sehat.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFF666666),
                        lineHeight = 24.sp,
                        letterSpacing = 0.25.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            // Start Button Section
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

@Preview(showBackground = true)
@Composable
fun GettingStartedScreenPreview() {
    GettingStartedScreen(
        onStartClick = {}
    )
}