package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.dao.FoodDao
import com.lilkoln.healthyliving.db.enums.FoodType

class FoodRepository(private val foodDao: FoodDao) {

    suspend fun createFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun findFoodById(id: Int): Food {
        return foodDao.findById(id)
    }

    suspend fun findAll(): List<Food> {
        return foodDao.loadAll()
    }

    suspend fun getFoodByType(type: FoodType): List<Food> {
        return foodDao.getFoodByType(type)
    }
}