package com.example.fitlogapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitlogapp.ioThread

@Database(entities = [DBTraining::class,DBExercise::class,DBTrainingType::class,DBExerciseType::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        const val NAME = "app-db"

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    val dao = INSTANCE?.appDao()
                    insertDummyData(dao)
                }
            }
        }

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    NAME
                )
                    .addCallback(roomCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

