package com.example.capstone

import com.example.capstone.Store
import retrofit2.Call
import retrofit2.http.*


interface RetrofitAPI {
        @GET("stores")
        fun requestStoreData(): Call<Store>
}