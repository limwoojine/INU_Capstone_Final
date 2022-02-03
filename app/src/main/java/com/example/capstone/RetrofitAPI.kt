package com.example.capstone

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*


interface RetrofitAPI {
        @GET("stores")
        fun requestStoreData(): Call<store>

}