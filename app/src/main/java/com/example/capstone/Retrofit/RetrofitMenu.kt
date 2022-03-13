package com.example.capstone.Retrofit

import com.example.capstone.data.Menu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMenu {

    @GET("store/{id}")
    fun requestFoodData(@Path("id") id: Int):
            Call<Menu>
}