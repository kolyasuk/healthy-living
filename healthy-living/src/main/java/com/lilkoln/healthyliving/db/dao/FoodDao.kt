package com.lilkoln.healthyliving.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lilkoln.healthyliving.db.Food
import com.lilkoln.healthyliving.db.User

@Dao
interface FoodDao {
    @Query("SELECT * FROM Food")
    suspend fun loadAll(): List<Food>

    @Delete
    suspend fun deleteFood(Food: Food)

    @Query("SELECT * from Food where name==:name limit 1")
    suspend fun getFoodByName(name: String): Food

    @Insert
    suspend fun insertFood(Food: Food)
}