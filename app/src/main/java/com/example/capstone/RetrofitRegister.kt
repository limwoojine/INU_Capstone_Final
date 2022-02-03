package com.example.capstone

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitRegister {

    @FormUrlEncoded
    @POST("register")
    fun RequestRegister (
        @Field("id") id:String,
        @Field("pw") pw:String,
        @Field("nickname") nickname:String,
        @Field("tel") tel:String) :  Call<register>

}