package com.adrian.mvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {

    @Query("SELECT * FROM posteos")
    fun getAllPosts(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(postEntityList : List<PostEntity>)

    @Query("DELETE FROM posteos")
    fun deleteAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPost(post : PostEntity)

}