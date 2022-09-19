package com.lilkoln.healthyliving.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lilkoln.healthyliving.db.dao.UserDao

@Database(entities = [User::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao



    companion object {
        var INSTANCE: AppDataBase? = null

        private var MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL")
                database.execSQL("ALTER TABLE user ADD COLUMN weight NUMERIC NOT NULL")
                database.execSQL("ALTER TABLE user ADD COLUMN height NUMERIC NOT NULL")
            }

        }

        fun getUserDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "healthy_living_db"
                ).addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE
        }

        fun cleanDbObject() {
            INSTANCE = null
        }

    }
}