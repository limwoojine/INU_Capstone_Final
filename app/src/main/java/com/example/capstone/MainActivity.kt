package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.adapter.StoreAdapter
import com.example.capstone.data.StoreData
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var storeAdapter: StoreAdapter

    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    var data: StoreData? = null
    var storeList: List<StoreData.Data>? = null

    private lateinit var recyclerView_store: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_store = findViewById(R.id.recyclerview_main)
        drawerLayout = findViewById(R.id.main_drawer_layout)
        navigationView = findViewById(R.id.main_navigationView)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.navi_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // 뒤로가기 버튼 활성화 여부
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navigationView.setNavigationItemSelectedListener(this)
        getStoreList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Navigation 열리고 뒤로가기 눌렀을 때
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
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

}