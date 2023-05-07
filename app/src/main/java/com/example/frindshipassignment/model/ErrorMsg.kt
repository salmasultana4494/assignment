package com.example.frindshipassignment.model


import com.google.gson.annotations.SerializedName

data class ErrorMsg(
    @SerializedName("field")
    var `field`: String,
    @SerializedName("message")
    var message: String
)