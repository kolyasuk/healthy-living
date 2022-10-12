package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.dao.FoodDao
import com.lilkoln.healthyliving.db.dao.NutritionUnitDao
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.entity.NutritionUnitAndFood

class NutritionUnitRepository(private val nutritionUnitDao: NutritionUnitDao) {

    suspend fun createNutritionUnit(food: NutritionUnit): Long {
        return nutritionUnitDao.insertNutritionUnit(food)
    }

    suspend fun checkNutritionUnit(id: Int, checked: Boolean) {
        nutritionUnitDao.checkNutritionUnit(id, checked)
    }

    suspend fun findAll(): List<NutritionUnit> {
        return nutritionUnitDao.loadAll()
    }

    suspend fun getNutritionUnitFood(): List<NutritionUnitAndFood> {
        return nutritionUnitDao.getNutritionUnitFood()
    }
}