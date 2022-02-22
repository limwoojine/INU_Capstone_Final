package com.example.capstone

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitKey {
    @FormUrlEncoded
    @POST("verifyCode/")
    fun requestResponse(@Field("key") key:String): Call<code>
}