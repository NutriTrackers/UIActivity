package com.eunoiamo.nutritracker.presentation.pages

import LoginViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.component.RoundedTextField
import com.eunoiamo.nutritracker.presentation.component.TopBarWithBackButton
import com.eunoiamo.nutritracker.presentation.utils.CustomToast
import com.eunoiamo.nutritracker.ui.theme.blue500
import com.eunoiamo.nutritracker.ui.theme.blue700


@Preview
@Composable
private fun loginpage () {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopBarWithBackButton(
                navController = navController,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
                    .padding(vertical = 120.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome Back !",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 140.dp),
                    color = Color.Black,
                )
                RoundedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    leadingIcon = Icons.Default.Email
                )
                Spacer(modifier = Modifier.height(12.dp))
                RoundedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        viewModel.loginUser(
                            email = email,
                            password = password,
                            onSuccess = {
                                CustomToast.show(context, "Login Successful", isSuccess = true)
                                navController.navigate("homeScreen")
                            },
                            onError = { errorMessage ->
                                CustomToast.show(context, errorMessage, isSuccess = false)
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors( blue500 )
                ) {
                    Text("Login",
                        color = Color.White,
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                val annotatedText = buildAnnotatedString {
                    append("Don't have an account? ")
                    pushStringAnnotation(
                        tag = "SIGN_UP",
                        annotation = "signUp"
                    )
                    withStyle(style = SpanStyle(color = blue700, fontWeight = FontWeight.Bold)) {
                        append("Sign up")
                    }
                    pop()
                }
                ClickableText(
                    text = annotatedText,
                    style = TextStyle(color = Color.DarkGray),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "SIGN_UP", start = offset, end = offset)
                            .firstOrNull()?.let {
                                navController.navigate("signUp")
                            }
                    }
                )
            }
        }
    )
}

