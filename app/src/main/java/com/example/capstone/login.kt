package com.example.capstone

import com.google.gson.annotations.SerializedName

data class login(
    @SerializedName("id") val id : String,
    @SerializedName("pw") var pw : String
)
