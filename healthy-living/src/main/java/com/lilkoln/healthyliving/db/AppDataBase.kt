package com.lilkoln.healthyliving.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lilkoln.healthyliving.db.dao.FoodDao
import com.lilkoln.healthyliving.db.dao.NutritionDao
import com.lilkoln.healthyliving.db.dao.NutritionUnitDao
import com.lilkoln.healthyliving.db.dao.UserDao
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.entity.User
import com.lilkoln.healthyliving.util.Converters

@Database(entities = [User::class, Food::class, NutritionUnit::class, Nutrition::class], version = 2, exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)]
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getFoodDao(): FoodDao
    abstract fun getNutritionUnitDao(): NutritionUnitDao
    abstract fun getNutritionDao(): NutritionDao

    companion object {
        var INSTANCE: AppDataBase? = null

        private var MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("INSERT INTO food (ID, name, description, type, calories, proteins, period, position) " +
                        "VALUES (null, 'Протеїн', '', 'SNACK', 120, 24, 1, 4)")
                database.execSQL("INSERT INTO food (ID, name, description, type, calories, proteins, period, position) " +
                        "VALUES (null, 'Яйце варене', '', 'LUNCH', 152, 13, 3, 0)")
                database.execSQL("INSERT INTO food (ID, name, description, type, calories, proteins, period, position) " +
                        "VALUES (null, 'Гречка з курячою вареною грудинкою', '', 'DINNER', 151, 29, 3, 0)")
                database.execSQL("INSERT INTO food (ID, name, description, type, calories, proteins, period, position) " +
                        "VALUES (null, 'Салат огірки і помідори', '', 'SUPPER', 90, 1, 2, 0)")
                database.execSQL("INSERT INTO food (ID, name, description, type, calories, proteins, period, position) " +
                        "VALUES (null, 'Яблуко', '', 'SNACK', 63, 1, 2, 2)")
            }
        }

        private var MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        private var MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        fun getUserDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "healthy_living_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE
        }

        fun cleanDbObject() {
            INSTANCE = null
        }

    }
}