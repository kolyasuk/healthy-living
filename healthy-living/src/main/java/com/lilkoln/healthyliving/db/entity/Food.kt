package com.lilkoln.healthyliving.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lilkoln.healthyliving.db.enums.FoodType
import java.io.Serializable

@Entity(tableName = "food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "type")
    var type: FoodType,
    @ColumnInfo(name = "calories")
    var calories: Int,
    @ColumnInfo(name = "proteins")
    var proteins: Int,
    @ColumnInfo(name = "period")
    var period: Int,
    @ColumnInfo(name = "position")
    var position: Int
) : Serializable