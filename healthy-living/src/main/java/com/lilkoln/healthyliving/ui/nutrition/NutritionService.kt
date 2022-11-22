package com.lilkoln.healthyliving.ui.nutrition

import android.annotation.SuppressLint
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.enums.FoodType
import com.lilkoln.healthyliving.db.enums.MeasureUnit
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.*

class NutritionService(private val mainActivityViewModel: MainActivityViewModel) {

    @SuppressLint("NewApi")
    suspend fun constructNutrition(map: HashMap<FoodType, Int>) {
        val nutrition = Nutrition(null, map[FoodType.LUNCH]!!, map[FoodType.SNACK]!!, map[FoodType.DINNER]!!, map[FoodType.SNACK_2]!!, map[FoodType.SUPPER]!!, LocalDate.now())
        mainActivityViewModel.createNutrition(nutrition)
    }

    suspend fun constructNutritionUnit(userCalories: Int, food: Food, percent: Int) {
        val foodPortionRate = getFoodAmount(userCalories, food.calories, percent)
        val nutritionUnit = NutritionUnit(null, food.id, foodPortionRate * 100, MeasureUnit.CALORIES, false,
            (foodPortionRate * food.calories).toInt(), (foodPortionRate * food.proteins).toInt())

        mainActivityViewModel.createNutritionUnit(nutritionUnit, food.type)
    }

    private fun getFoodAmount(userCalories: Int, foodCalories: Int, percent: Int) : Float {
        return userCalories.toFloat() * (percent.toFloat()/100f) / foodCalories.toFloat()
    }


}