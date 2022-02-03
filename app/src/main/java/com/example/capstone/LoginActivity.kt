package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        var LoginId:EditText = findViewById(R.id.login_id)
        var LoginPw:EditText = findViewById(R.id.login_pw)

        val loginButton: Button = findViewById(R.id.login_loginbutton)
        val registerButton: Button = findViewById(R.id.login_signupbutton)

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService = retrofit.create(RetrofitLogin::class.java)



        loginButton.setOnClickListener {
            val id:String = LoginId.text.toString()
            val pw:String = LoginPw.text.toString()
            loginService.requestLogin(id, pw).enqueue(object: Callback<login> {
                override fun onResponse(call: Call<login>, response: Response<login>) {
                    if (response.isSuccessful()) {
                        if (id == response.body()!!.id && pw == response.body()!!.pw ) {
                            Log.d("Login", "success")
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 다시 확인해주세요." , Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<login>, t: Throwable) {
                    Log.d("Login", "Fail")
                }

            })

        }
    }
}