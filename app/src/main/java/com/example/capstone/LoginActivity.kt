package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityLoginBinding
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        var LoginId:EditText = findViewById(R.id.login_id)
        var LoginPw:EditText = findViewById(R.id.login_pw)

        val loginButton: Button = findViewById(R.id.login_loginbutton)
        val registerButton: Button = findViewById(R.id.login_signupbutton)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
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
            val user = HashMap<String, String>()
            user["id"] = id
            user["pw"] = pw
            loginService.requestLogin(user).enqueue(object: Callback<login> {
                override fun onResponse(call: Call<login>, response: Response<login>) {
                    if (response.body() != null) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    } else {
                        val error = response.errorBody()!!.string()
                        if (error == "100") {
                            Toast.makeText(this@LoginActivity, "가입되지 않은 사용자 입니다.",Toast.LENGTH_SHORT).show()
                        } else if (error == "200") {
                            Toast.makeText(this@LoginActivity, "잘못된 비밀번호 입니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<login>, t: Throwable) {
                    Log.d("Login", "Fail")
                    Toast.makeText(this@LoginActivity, "로그인 에러" , Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}