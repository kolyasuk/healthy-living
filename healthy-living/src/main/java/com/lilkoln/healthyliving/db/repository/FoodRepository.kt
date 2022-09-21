package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.Food
import com.lilkoln.healthyliving.db.User
import com.lilkoln.healthyliving.db.dao.FoodDao
import com.lilkoln.healthyliving.db.dao.UserDao

class FoodRepository(private val foodDao: FoodDao) {

    suspend fun createFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun findAll(): List<Food> {
        return foodDao.loadAll()
    }
}