package com.adrian.mvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.adrian.mvvm.MiApp
import com.adrian.mvvm.database.PostEntity
import com.adrian.mvvm.repository.retrofit.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepository {

    fun getPostsFromServer(){
        Log.d("Post:", "Getting data from server")
        ApiAdapter()
            .getApiService().getPosts().enqueue(object : Callback<List<PostEntity>> {
                override fun onResponse(call: Call<List<PostEntity>>, response: Response<List<PostEntity>>) {
                    when {
                        response.isSuccessful -> {
                            Log.d("Post:", "Getting data success")
                            var posts: List<PostEntity>? = response?.body()

                            if (posts != null){
                                insertData(posts)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                    t?.printStackTrace()
                    Log.d("Post:", "Getting data failed")
                }
            })
    }

    private fun insertData(posts: List<PostEntity>){
        Thread(Runnable {
            Log.d("Post:", "Inserting data")
            MiApp.database.postDao().deleteAllPosts()
            MiApp.database.postDao().insertAllPosts(posts)
        }).start()
    }

    fun getAllPosts() : LiveData<List<PostEntity>>
    {
        Log.d("Post:", "Getting data from database")
        return MiApp.database!!.postDao().getAllPosts()
    }

    fun deleteAllPosts(){
        Thread(Runnable {
            MiApp.database.postDao().deleteAllPosts()
        }).start()
    }

    fun addNewPost(post: PostEntity){
        Thread(Runnable {
            MiApp.database.postDao().insertNewPost(post)
        }).start()
    }

}