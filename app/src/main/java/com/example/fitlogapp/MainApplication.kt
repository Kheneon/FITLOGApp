package com.example.fitlogapp

import android.app.Application
import androidx.room.Room
import com.example.fitlogapp.db.AppDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainApplication : Application() {

    companion object{
        lateinit var appDB : AppDB
    }

    override fun onCreate() {
        super.onCreate()
        /*runBlocking {
            launch(Dispatchers.IO) {
                applicationContext.deleteDatabase("app-db")
            }.join()
        }*/
        appDB = AppDB.getDatabase(applicationContext)
    }
}