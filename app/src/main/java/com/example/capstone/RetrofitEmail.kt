package com.example.capstone

import retrofit2.Call
import retrofit2.http.*

interface RetrofitEmail {

    @FormUrlEncoded
    @POST("email")
    //@Headers("accept: application/json",
      //  "content-type: application/json")
    fun requestKey(
        @Field("email") param: String): Call<emailkey>
}
