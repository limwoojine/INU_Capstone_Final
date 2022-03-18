package com.example.capstone.Retrofit

import com.example.capstone.data.ReviewPost
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitReview {

    @POST("review/{foodid}")
    fun postReview(@Path("foodid") foodid: Int) : Call<ReviewPost>
}