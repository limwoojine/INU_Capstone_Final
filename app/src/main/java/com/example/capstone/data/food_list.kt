package com.example.capstone.data

import com.google.gson.annotations.SerializedName

data class Food(
    var `food`: food_list
) {
data class food_list(
    var name: String,
    var foodListDto: List<FoodListDto>,
    var phoneNumber: String,
    var foodOrigin: String
    ) {


data class FoodListDto (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name:String,
    @SerializedName("price") var price: String,
    @SerializedName("status") var status:Any
    )
}
}



