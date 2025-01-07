package com.eunoiamo.nutritracker.data.model

data class PredictRequest (
    val age: Int,
    val weight: Float,
    val height: Float,
    val gender: String,
    val activity_level: String
)