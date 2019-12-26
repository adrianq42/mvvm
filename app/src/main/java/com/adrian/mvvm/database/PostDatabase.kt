package com.adrian.mvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PostEntity::class), version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}