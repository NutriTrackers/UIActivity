package com.eunoiamo.nutritracker.data.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoiamo.nutritracker.data.api.ApiClient
import com.eunoiamo.nutritracker.data.model.PredictRequest
import com.eunoiamo.nutritracker.data.model.PredictResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictViewModel : ViewModel() {
    private val apiService = ApiClient.apiService

    fun predict(
        age: Int,
        weight: Float,
        height: Float,
        gender: String,
        activityLevel: String,
        onSuccess: (PredictResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Simulasi request prediksi
                val request = PredictRequest(age, weight, height, gender, activityLevel)
                val response = apiService.predict(request)

                if (response.isSuccessful) {
                    response.body()?.let {
                        // Menyimpan hasil prediksi ke ApiClient
                        ApiClient.setPredictionResult(it)
                        onSuccess(it)
                        Log.e("ViewModel Update", "New Prediction: $it")
                    } ?: run {
                        Log.e("ViewModel Error", "Response body is null")
                    }
                } else {
                    Log.e("ViewModel Error", "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                onError("Error: ${e.message}")
                Log.e("ViewModel Error", "Exception: ${e.message}")
            }
        }
    }

    // Fungsi untuk menghapus hasil prediksi
    fun clearPredictionResult() {
        ApiClient.clearPredictionResult()
    }
}
