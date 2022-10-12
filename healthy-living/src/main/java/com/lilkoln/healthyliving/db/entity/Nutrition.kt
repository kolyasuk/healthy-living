package com.lilkoln.healthyliving.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Entity(tableName = "nutrition")
data class Nutrition (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "lunch_id")
    val lunchId: Int,
    @ColumnInfo(name = "first_snack_id")
    val firstSnackId: Int,
    @ColumnInfo(name = "dinner_id")
    val dinnerId: Int,
    @ColumnInfo(name = "second_snack_id")
    val secondSnackId: Int,
    @ColumnInfo(name = "supper_id")
    val supperId: Int,
    @ColumnInfo(name = "date")
    val date: LocalDate,
) : Serializable