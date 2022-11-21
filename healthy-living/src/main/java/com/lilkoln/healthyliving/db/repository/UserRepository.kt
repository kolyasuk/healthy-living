package com.lilkoln.healthyliving.db.repository

import com.lilkoln.healthyliving.db.entity.User
import com.lilkoln.healthyliving.db.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun createUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun findUserByEmail(): User {
        return userDao.loadFirst()
    }

    suspend fun getUserCalories(): Int {
        return userDao.getUserCalories()
    }

    suspend fun getUser(): User {
        return userDao.loadFirst()
    }
}