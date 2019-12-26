package com.adrian.mvvm

import android.app.Application
import androidx.room.Room
import com.adrian.mvvm.database.PostsDatabase

class MiApp: Application() {

    companion object {
        lateinit var database: PostsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, PostsDatabase::class.java, "posts-db").build()
    }
}