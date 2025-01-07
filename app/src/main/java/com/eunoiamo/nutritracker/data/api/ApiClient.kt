package com.eunoiamo.nutritracker.data.api

import com.eunoiamo.nutritracker.data.model.PredictResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://cf90-140-213-44-63.ngrok-free.app/api/accounts/"
    private var predictionResult: PredictResponse? = null  // Variabel untuk menyimpan hasil prediksi sementara

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // Fungsi untuk menyimpan hasil prediksi
    fun setPredictionResult(result: PredictResponse) {
        predictionResult = result
    }

    // Fungsi untuk mengambil hasil prediksi yang disimpan
    fun getPredictionResult(): PredictResponse? {
        return predictionResult
    }

    // Fungsi untuk menghapus hasil prediksi
    fun clearPredictionResult() {
        predictionResult = null
    }
}
