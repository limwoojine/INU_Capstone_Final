package com.example.capstone.api

import com.example.capstone.data.food_list
import com.example.capstone.data.store
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitMenu {

    @GET("food")
    fun requestFoodData(): Call<food_list>
}