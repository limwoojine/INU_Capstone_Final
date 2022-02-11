package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.api.RetrofitAPI
import com.example.capstone.api.RetrofitMenu
import com.example.capstone.data.food
import com.example.capstone.data.food_list
import com.example.capstone.data.store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitMenu::class.java)

        val storename: TextView = findViewById(R.id.store_name2)
        val storenumber: TextView = findViewById(R.id.store_number)

        if (intent.hasExtra("name")) {
            storename.text = intent.getStringExtra("name")
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
        if (intent.hasExtra("number")) {
            storenumber.text = intent.getStringExtra("number")
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }


        retrofitService.requestFoodData().enqueue(object : Callback<food_list> {
            override fun onResponse(call: Call<food_list>, response: Response<food_list>) {
                Log.d("food list", "${response.body()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_food)
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MenuActivity)
                            adapter = MenuAdapter(context, it.data)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<food_list>, t: Throwable) {
                Log.d("food list", "통신 에러")
            }
        })
    }
}