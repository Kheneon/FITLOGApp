package com.example.fitlogapp

import android.app.Application
import com.example.fitlogapp.db.AppDB

class MainApplication : Application() {

    companion object{
        lateinit var appDB : AppDB
        var actualTrainingID : Int = 0
    }

    override fun onCreate() {
        super.onCreate()
        appDB = AppDB.getDatabase(applicationContext)
    }
}