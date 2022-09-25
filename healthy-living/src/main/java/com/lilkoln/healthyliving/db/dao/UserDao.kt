package com.lilkoln.healthyliving.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lilkoln.healthyliving.db.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun loadAll(): List<User>

    @Query("SELECT * FROM User order by id asc limit 1")
    suspend fun loadFirst(): User

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * from User where name==:name")
    suspend fun getUserListByName(name: String): List<User>

    @Insert
    suspend fun insertUser(user: User)
}