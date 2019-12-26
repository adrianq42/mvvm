package com.adrian.mvvm.repository.retrofit

import com.adrian.mvvm.database.PostEntity
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("todos")
    fun getPosts(): Call<List<PostEntity>>
}