package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.dao.NutritionDao
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionAndNutritionUnit

class NutritionRepository(private val nutritionDao: NutritionDao) {

    suspend fun createNutrition(food: Nutrition) {
        nutritionDao.insertNutrition(food)
    }

    suspend fun findAll(): List<Nutrition> {
        return nutritionDao.loadAll()
    }

    suspend fun getNutritionUnits(nutritionId: Int): List<NutritionAndNutritionUnit> {
        return nutritionDao.getNutritionAndNutritionUnit(nutritionId)
    }
}