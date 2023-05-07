package com.example.frindshipassignment.api

import com.example.frindshipassignment.model.RequestBody
import com.example.frindshipassignment.model.UserInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

interface EndPoints {
    @GET("users")
    suspend fun getUsers(): Response<List<UserInfo>>
    @POST("users")
    suspend fun createUser(@Body requestBody: RequestBody): Response<UserInfo>
    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Int,@Body requestBody: RequestBody): Response<UserInfo>
}