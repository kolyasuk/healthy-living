package com.lilkoln.healthyliving.ui

data class NutritionModel(
    val lunchId: Int,
    val lunchMeal: String,
    val lunchChecked: Boolean,
    val firstSnackId: Int,
    val firstSnack: String,
    val firstSnackChecked: Boolean,
    val dinnerId: Int,
    val dinnerMeal: String,
    val dinnerChecked: Boolean,
    val secondSnackId: Int,
    val secondSnack: String,
    val secondSnackChecked: Boolean,
    val supperId: Int,
    val supperMeal: String,
    val supperChecked: Boolean,
)
