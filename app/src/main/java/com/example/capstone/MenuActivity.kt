package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.Retrofit.RetrofitMenu
import com.example.capstone.adapter.MenuAdapter
import com.example.capstone.data.Food
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class MenuActivity : AppCompatActivity() {

    var menuList: Food.food_list? = null
    var foodValue: List<Food.food_list.FoodListDto>? = null

    private lateinit var recyclerView_menu: RecyclerView
    private lateinit var storeName: TextView
    private lateinit var storeNumber: TextView
    private lateinit var foodOrigin: TextView
    private var storeId by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        recyclerView_menu = findViewById(R.id.recyclerview_food)
        storeName = findViewById(R.id.store_name2)
        storeNumber = findViewById(R.id.store_number)
        foodOrigin = findViewById(R.id.food_origin)

        if (intent.hasExtra("id")) {
            val storeId = intent?.getStringExtra("id")?.toInt()
        }
        getMenuList(storeId)

    }
    private fun getMenuList(id: Int) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitMenu::class.java)

        retrofitService
            .requestFoodData(id)
            .enqueue(object : Callback<Food.food_list> {
                override fun onResponse(call: Call<Food.food_list>, response: Response<Food.food_list>) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            // do something
                            menuList = response.body()
                            foodValue = menuList?.foodListDto
                            Log.d("메뉴 불러오기 성공!", response!!.body().toString())
                            //인증한 adapter에 Member 데이터 넣기
                            setMenuAdapter(foodValue)
                            storeName.text = menuList?.name
                            storeNumber.text = menuList?.phoneNumber
                            foodOrigin.text = menuList?.foodOrigin
                        }
                }

                override fun onFailure(call: Call<Food.food_list>, t: Throwable) {
                    Log.d("메뉴 통신 실패", "${t.message.toString()}")
                }
            })
    }
    private fun setMenuAdapter(menu: List<Food.food_list.FoodListDto>?) {
        val mAdapter = MenuAdapter(menu!!)
        recyclerView_menu.adapter = mAdapter
        recyclerView_menu.layoutManager = LinearLayoutManager(this)
        mAdapter.notifyDataSetChanged()
        recyclerView_menu.setHasFixedSize(true)
    }
}