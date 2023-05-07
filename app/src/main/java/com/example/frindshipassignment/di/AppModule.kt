package com.example.frindshipassignment.di

import com.example.frindshipassignment.api.AuthorizationInterceptor
import com.example.frindshipassignment.api.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        val token = "5f54c461fb7d6b345b3a770fa4586795ec1125bb80459ed9daba5f1c26cf26e0"
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token))
            .build()

        return Retrofit.Builder().baseUrl("https://gorest.co.in/public/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun friendshipAPI(retrofit: Retrofit) : EndPoints {
        return retrofit.create(EndPoints::class.java)
    }
}