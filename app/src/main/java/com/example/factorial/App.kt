package com.example.factorial

import android.app.Application

class App: Application() {

    lateinit var dbRepository: DatabaseRepository

    override fun onCreate() {
        super.onCreate()

        val dao = HistoryDatabase.getInstance(this).itemDao()
        dbRepository = DatabaseRepository(dao)
    }
}