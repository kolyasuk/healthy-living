package com.lilkoln.healthyliving.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class NutritionAndNutritionUnit(
    @Embedded val nutrition: Nutrition,
    @Relation(
        parentColumn = "lunch_id",
        entityColumn = "id"
    )
    val lunch: NutritionUnit,
    @Relation(
        parentColumn = "first_snack_id",
        entityColumn = "id"
    )
    val firstSnack: NutritionUnit,
    @Relation(
        parentColumn = "dinner_id",
        entityColumn = "id"
    )
    val dinner: NutritionUnit,
    @Relation(
        parentColumn = "second_snack_id",
        entityColumn = "id"
    )
    val secondSnack: NutritionUnit,
    @Relation(
        parentColumn = "supper_id",
        entityColumn = "id"
    )
    val supper: NutritionUnit
)
