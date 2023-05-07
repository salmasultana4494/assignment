package com.example.frindshipassignment.di

import com.example.frindshipassignment.api.EndPoints
import com.example.frindshipassignment.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl("https://gorest.co.in/public/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun friendshipAPI(retrofit: Retrofit) : EndPoints {
        return retrofit.create(EndPoints::class.java)
    }
}