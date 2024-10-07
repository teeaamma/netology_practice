package com.example.netology_practice

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        GameStatsEntity::class,
    ]
)
abstract class GamesDatabase: RoomDatabase() {

    companion object {

        private var INSTANCE: GamesDatabase? = null
        private var LOCK = Any()
        private const val DB_NAME = "stats.db"

        fun getInstance(application: Application): GamesDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    GamesDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = db
                return db
            }
        }

    }

    abstract fun getGameStatsDao() : GameStatsDao
}