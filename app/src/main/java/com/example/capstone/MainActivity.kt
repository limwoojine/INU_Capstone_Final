package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.adapter.StoreAdapter
import com.example.capstone.data.StoreData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var storeAdapter: StoreAdapter

    var data: StoreData? = null
    var storeList: List<StoreData.Data>? = null

    private lateinit var recyclerView_store: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_store = findViewById(R.id.recyclerview_main)
        getStoreList()
        /*setSupportActionBar(findViewById(R.id.loginToolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)*/
    }

    private fun getStoreList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitAPI1::class.java)


        retrofitService
            .requestStoreData()
            .enqueue(object : Callback<StoreData> {
                override fun onResponse(call: Call<StoreData>, response: Response<StoreData>) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            // do something
                            data = response.body()
                            Log.d("성공성공!", response!!.body().toString())
                            //인증한 adapter에 Member 데이터 넣기
                            setStoreAdapter(it.data)

                        }
                }

                override fun onFailure(call: Call<StoreData>, t: Throwable) {
                    Log.d("통신 실패", "${t.message.toString()}")
                }
            })
    }
    private fun setStoreAdapter(storeList: List<StoreData.Data>) {
        val mAdapter = StoreAdapter(storeList)
        recyclerView_store.adapter = mAdapter
        recyclerView_store.layoutManager = LinearLayoutManager(this)
        mAdapter.notifyDataSetChanged()
        recyclerView_store.setHasFixedSize(true)
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }*/
}