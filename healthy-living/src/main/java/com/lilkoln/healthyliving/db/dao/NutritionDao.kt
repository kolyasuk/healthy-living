package com.lilkoln.healthyliving.db.dao

import androidx.room.*
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionAndNutritionUnit
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.entity.NutritionUnitAndFood
import java.time.LocalDate
import java.util.*

@Dao
interface NutritionDao {
    @Query("SELECT * FROM Nutrition")
    suspend fun loadAll(): List<Nutrition>

    @Delete
    suspend fun deleteNutrition(Nutrition: Nutrition)

    @Insert
    suspend fun insertNutrition(Nutrition: Nutrition)

    @Transaction
    @Query("SELECT * FROM Nutrition where id==:nutritionId")
    suspend fun getNutritionAndNutritionUnit(nutritionId: Int): List<NutritionAndNutritionUnit>

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM Nutrition WHERE date==:date LIMIT 1)")
    suspend fun existForDate(date: LocalDate): Boolean

    @Transaction
    @Query("SELECT * FROM Nutrition where date==:date")
    suspend fun getNutritionAndNutritionUnitByDate(date: LocalDate): NutritionAndNutritionUnit
}