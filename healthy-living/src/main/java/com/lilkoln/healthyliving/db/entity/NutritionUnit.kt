package com.lilkoln.healthyliving.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lilkoln.healthyliving.db.enums.MeasureUnit
import java.io.Serializable

@Entity(tableName = "nutrition_unit")
data class NutritionUnit (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "amount")
    val amount: Float,
    @ColumnInfo(name = "unit")
    val unit: MeasureUnit,
    @ColumnInfo(name = "checked")
    val checked: Boolean,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "proteins")
    val proteins: Int
) : Serializable