package com.eunoiamo.nutritracker.data.model
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?
)