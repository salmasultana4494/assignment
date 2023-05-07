package com.example.frindshipassignment.repository

import com.example.frindshipassignment.api.EndPoints
import com.example.frindshipassignment.model.RequestBody
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val endPoints: EndPoints
) {

    suspend fun getUsers() = endPoints.getUsers()
    suspend fun createUser(requestBody: RequestBody) = endPoints.createUser(requestBody)
    suspend fun updateUser(userID: Int, requestBody: RequestBody) = endPoints.updateUser(userID,requestBody)

}