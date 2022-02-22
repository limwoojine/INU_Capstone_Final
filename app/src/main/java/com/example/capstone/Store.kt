package com.example.capstone

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("data") val data : List<Test>
)
data class Test (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name:String,
    @SerializedName("introduce") val introduce: String
)
