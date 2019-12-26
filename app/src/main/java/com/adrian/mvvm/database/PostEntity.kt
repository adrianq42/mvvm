package com.adrian.mvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posteos")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int = 0,
    val title: String = "",
    val completed: Boolean = false
)