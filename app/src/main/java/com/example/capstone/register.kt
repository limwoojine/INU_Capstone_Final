package com.example.capstone

import com.google.gson.annotations.SerializedName

data class register(
    val email: String,
    val nickName: String,
    val password: String,
)

data class code(
    val response: String
)

data class emailkey(
    @SerializedName("email") var email: String
)
