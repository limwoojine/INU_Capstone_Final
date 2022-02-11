package com.example.capstone.data

data class food_list(
    var data : List<food>
)
data class food (
    var introduce: String,
    var food_origin: String,
    var price: String,
    var rating: String,
    var name:String,
    var image:String,
)
