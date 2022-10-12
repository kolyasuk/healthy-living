package com.lilkoln.healthyliving.db.dao

import androidx.room.*
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.entity.NutritionUnitAndFood

@Dao
interface NutritionUnitDao {
    @Query("SELECT * FROM nutrition_unit")
    suspend fun loadAll(): List<NutritionUnit>

    @Delete
    suspend fun deleteNutritionUnit(NutritionUnit: NutritionUnit)

    @Insert
    suspend fun insertNutritionUnit(NutritionUnit: NutritionUnit): Long

    @Query("UPDATE nutrition_unit SET checked=:checked where id==:id")
    suspend fun checkNutritionUnit(id: Int, checked: Boolean)

    @Transaction
    @Query("SELECT * FROM nutrition_unit")
    suspend fun getNutritionUnitFood(): List<NutritionUnitAndFood>
}