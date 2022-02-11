package com.example.capstone.api

import com.example.capstone.data.store
import retrofit2.Call
import retrofit2.http.*


interface RetrofitAPI {
        @GET("stores")
        fun requestStoreData(): Call<store>

}