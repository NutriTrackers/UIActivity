package com.eunoiamo.nutritracker.presentation.pages

import SignUpViewModel
import android.annotation.SuppressLint
import android.widget.Toast

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.presentation.component.RoundedTextField
import com.eunoiamo.nutritracker.presentation.component.TopBarWithBackButton
import com.eunoiamo.nutritracker.ui.theme.blue500
import com.eunoiamo.nutritracker.ui.theme.blue700
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eunoiamo.nutritracker.presentation.utils.CustomToast


@Preview
@Composable
fun signUpPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = viewModel()
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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
                    .padding(vertical = 80.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 40.dp),
                    color = Color.Black
                )

                RoundedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    leadingIcon = Icons.Default.Person)

                Spacer(modifier = Modifier.height(8.dp))

                RoundedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    leadingIcon = Icons.Default.Email)

                Spacer(modifier = Modifier.height(8.dp))

                RoundedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(8.dp))
                RoundedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirm Password",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        if (password != confirmPassword) {
                            CustomToast.show(context, "Passwords do not match", isSuccess = false)
                        } else {
                            viewModel.registerUser(username, email, password, onSuccess = {
                                CustomToast.show(context, "Registration Successful", isSuccess = true)
                                navController.navigate("login")
                            }, onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors( blue500 )
                ) {
                    Text("Sign Up",
                        color = Color.White,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                val annotatedText = buildAnnotatedString {
                    append("Already have an account? ")
                    pushStringAnnotation(
                        tag = "SIGN_IN",
                        annotation = "signIn"
                    )
                    withStyle(style = SpanStyle(color = blue700, fontWeight = FontWeight.Bold)) {
                        append("Sign in")
                    }
                    pop()
                }
                ClickableText(
                    text = annotatedText,
                    style = TextStyle(color = Color.DarkGray),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "SIGN_IN", start = offset, end = offset)
                            .firstOrNull()?.let {
                                navController.navigate("login")
                            }
                    }
                )
            }
        }
    )
}