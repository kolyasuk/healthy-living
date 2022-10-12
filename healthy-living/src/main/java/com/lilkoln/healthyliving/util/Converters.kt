package com.lilkoln.healthyliving.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.room.TypeConverter
import java.sql.Timestamp
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time?.toLong()
//    }

    @SuppressLint("NewApi")
    @TypeConverter
    fun fromTimestamp(value: Long): LocalDate {
        return value.let {
            LocalDate.ofEpochDay(it)
        }
    }

    @SuppressLint("NewApi")
    @TypeConverter
    fun dateToTimestamp(date: LocalDate): Long {
        return date.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
    }
}