package com.lilkoln.healthyliving.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "type")
    var type: FoodType? = null,
    @ColumnInfo(name = "calories")
    var calories: Int? = null,
    @ColumnInfo(name = "proteins")
    var proteins: Int? = null,
    @ColumnInfo(name = "period")
    var period: Int? = null,
    @ColumnInfo(name = "position")
    var position: Int? = null
) : Serializable