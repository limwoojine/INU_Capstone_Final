package com.example.capstone

import com.google.gson.annotations.SerializedName

data class food_list(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val food_name:String,
    @SerializedName("price") val food_price:Int,
    @SerializedName("status") val food_status:String,
    @SerializedName("storeName") val store_name:String,
    @SerializedName("reviewList") val food_review:String
)

