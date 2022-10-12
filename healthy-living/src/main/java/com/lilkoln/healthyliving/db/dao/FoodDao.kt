package com.lilkoln.healthyliving.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.enums.FoodType

@Dao
interface FoodDao {
    @Query("SELECT * FROM Food")
    suspend fun loadAll(): List<Food>

    @Query("SELECT * FROM Food where id==:id")
    suspend fun findById(id: Int): Food

    @Delete
    suspend fun deleteFood(Food: Food)

    @Query("SELECT * from Food where name==:name limit 1")
    suspend fun getFoodByName(name: String): Food

    @Query("SELECT * from Food where type==:type")
    suspend fun getFoodByType(type: FoodType): List<Food>

    @Insert
    suspend fun insertFood(Food: Food)
}