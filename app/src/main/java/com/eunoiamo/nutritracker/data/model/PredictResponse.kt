package com.eunoiamo.nutritracker.data.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @SerializedName("bmi") val bmi: String,
    @SerializedName("bmr") val bmr: String,
    @SerializedName("calories_needed") val caloriesNeeded: String,
    @SerializedName("status_label") val statusLabel: String,
    @SerializedName("recommended_foods") val recommendedFoods: List<FoodItem>
)

data class FoodItem(
    @SerializedName("Food") val food: String,
    @SerializedName("Calories") val calories: Double,
    @SerializedName("Protein") val protein: Double,
    @SerializedName("Fat") val fat: Double,
    @SerializedName("Carbs") val carbs: Double,
    @SerializedName("Category") val category: String
)