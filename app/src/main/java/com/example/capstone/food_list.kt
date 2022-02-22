package com.example.capstone

data class food_list(
    var foodOrigin: String,
    var phoneNumber: String,
    var data : List<food>
)
data class food (
    var introduce: String,
    var price: String,
    var rating: String,
    var name:String,
    var status:Boolean,
    var image:String,
)
