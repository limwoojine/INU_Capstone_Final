package com.example.capstone

import com.example.capstone.register
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitRegister {

    @FormUrlEncoded
    @POST("email")
    fun RequestRegister (
        @Field("id") id:String,
        @Field("nickName") nickname:String,
        @Field("pw") pw:String) : Call<register>

}