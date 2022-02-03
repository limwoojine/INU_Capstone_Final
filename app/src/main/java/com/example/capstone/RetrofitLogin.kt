package com.example.capstone

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitLogin {
    @FormUrlEncoded
    @POST("login")
    fun requestLogin(
        @Field("id") id:String,
        @Field("pw") pw:String ): Call<login>
}