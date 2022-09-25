package com.lilkoln.healthyliving.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class NutritionUnitAndFood(
    @Embedded val nutritionUnit: NutritionUnit,
    @Relation(
        parentColumn = "food_id",
        entityColumn = "id"
    )
    val food: Food
)
