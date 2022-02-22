package com.example.capstone

import com.example.capstone.food_list
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMenu {

    @GET("store/{id}")
    fun requestFoodData(@Path("id") id: String):
            Call<food_list>
}