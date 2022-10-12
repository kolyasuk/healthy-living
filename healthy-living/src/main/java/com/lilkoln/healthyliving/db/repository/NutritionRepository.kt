package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.dao.NutritionDao
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionAndNutritionUnit
import java.time.LocalDate
import java.util.*

class NutritionRepository(private val nutritionDao: NutritionDao) {

    suspend fun createNutrition(nutrition: Nutrition) {
        nutritionDao.insertNutrition(nutrition)
    }

    suspend fun findAll(): List<Nutrition> {
        return nutritionDao.loadAll()
    }

    suspend fun existForDate(date: LocalDate): Boolean {
        return nutritionDao.existForDate(date)
    }

    suspend fun getNutritionUnits(nutritionId: Int): List<NutritionAndNutritionUnit> {
        return nutritionDao.getNutritionAndNutritionUnit(nutritionId)
    }

    suspend fun getNutritionUnitsByDate(date: LocalDate): NutritionAndNutritionUnit {
        return nutritionDao.getNutritionAndNutritionUnitByDate(date)
    }
}