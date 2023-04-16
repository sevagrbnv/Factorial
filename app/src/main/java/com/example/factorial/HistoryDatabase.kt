package com.example.factorial

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun itemDao(): HistoryItemDao

    companion object {

        private var INSTANCE: HistoryDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "history.db"

        fun getInstance(application: Application): HistoryDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    HistoryDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}