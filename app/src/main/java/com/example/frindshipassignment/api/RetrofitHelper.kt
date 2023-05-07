package com.example.frindshipassignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun retrofitInstance(): Retrofit {
        val baseUrl = "https://gorest.co.in/public/v2/"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}