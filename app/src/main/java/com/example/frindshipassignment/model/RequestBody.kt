package com.example.frindshipassignment.model


import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("email")
    var email: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String
)