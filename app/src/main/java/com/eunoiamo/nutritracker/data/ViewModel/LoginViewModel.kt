package com.eunoiamo.nutritracker.data.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoiamo.nutritracker.data.api.ApiClient
import com.eunoiamo.nutritracker.data.model.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {
    private val apiService = ApiClient.apiService

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loginUser(
        email: String,
        password: String,
        onSuccess: (String) -> Unit, // Pass the token in onSuccess
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = LoginRequest(email, password)
                val response = apiService.login(request)

                if (response.isSuccessful) {
                    // Extract the token from the response
                    val token = response.body()?.token // Assuming the response contains a token field

                    if (!token.isNullOrEmpty()) {
                        // Pass the token to onSuccess
                        onSuccess(token)
                    } else {
                        onError("Token not found in response")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    onError("Failed: $errorBody")
                }
            } catch (e: HttpException) {
                onError("Network error: ${e.message()}")
            } catch (e: IOException) {
                onError("IO error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

