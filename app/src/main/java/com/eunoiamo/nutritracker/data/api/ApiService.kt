package com.eunoiamo.nutritracker.data.api

import com.eunoiamo.nutritracker.data.model.LoginRequest
import com.eunoiamo.nutritracker.data.model.LoginResponse
import com.eunoiamo.nutritracker.data.model.SignUpRequest
import com.eunoiamo.nutritracker.data.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup/")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}