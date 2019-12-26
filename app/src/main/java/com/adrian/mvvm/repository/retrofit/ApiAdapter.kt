package com.adrian.mvvm.repository.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiAdapter {

    private lateinit var API_SERVICE: ApiService

    private var retrofit: Retrofit? = null

    private val okHttpClient = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getApiService(): ApiService {

        if (!::API_SERVICE.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            API_SERVICE = retrofit!!.create(ApiService::class.java)
        }

        return API_SERVICE
    }
}