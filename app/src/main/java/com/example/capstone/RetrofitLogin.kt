package com.example.capstone

import com.example.capstone.login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitLogin {
    @FormUrlEncoded
    @POST("login")
    fun requestLogin(
        @Body user : Map<String, String>): Call<login>
}