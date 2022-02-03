package com.example.capstone

import com.google.gson.annotations.SerializedName
import java.io.StringBufferInputStream

data class store(
    var data : List<Test>
    )
data class Test (
    var introduce: String,
    var food_origin: String,
    var number: String,
    var phone_number: String,
    var name:String,
    var image:String,
    var food: List<menu>
    )
data class menu (
    var price: Int,
    var name: String,
    var detail: String,
)



