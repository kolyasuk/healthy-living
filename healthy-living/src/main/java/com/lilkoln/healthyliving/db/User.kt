package com.lilkoln.healthyliving.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val firstName: String? = null,
    @ColumnInfo(name = "age")
    val age: Int? = null,
    @ColumnInfo(name = "weight")
    val weight: Float? = null,
    @ColumnInfo(name = "height")
    val height: Float? = null
) : Serializable