package com.example.capstone

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.signup_student)

        val registerButton: Button = findViewById(R.id.register_signupbutton)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var registerService = retrofit.create(RetrofitRegister::class.java)

        var check: TextView = findViewById(R.id.register_pwok)

        var pw: EditText = findViewById(R.id.register_pw)
        var repw: EditText = findViewById(R.id.register_repw)
        var id:EditText = findViewById(R.id.register_id)
        var name:EditText = findViewById(R.id.register_nickname)
        var telnumber:EditText = findViewById(R.id.register_tel)

        check.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (pw.text.toString() == repw.text.toString()) {
                    check.text = "비밀번호가 일치합니다."
                    check.setTextColor(Color.BLACK)
                    registerButton.isEnabled = true
                }
                else {
                    check.text = "비밀번호가 일치하지 않습니다."
                    check.setTextColor(Color.RED)
                    registerButton.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (pw.text.toString() == repw.text.toString()) {
                    check.text = "비밀번호가 일치합니다."
                    check.setTextColor(Color.BLACK)
                    registerButton.isEnabled = true
                }
                else {
                    check.text = "비밀번호가 일치하지 않습니다."
                    check.setTextColor(Color.RED)
                    registerButton.isEnabled = false
                }
            }
        })
        registerButton.setOnClickListener {
            val email = id.text.toString()
            val password = pw.text.toString()
            val nickname = name.text.toString()
            val tel = telnumber.text.toString()

            registerService.RequestRegister(email, password, nickname, tel).enqueue(object : Callback<register> {
                override fun onResponse(call: Call<register>, response: Response<register>) {
                    val result = response.body()
                    Toast.makeText(this@RegisterActivity, "회원가입이 되었습니다." , Toast.LENGTH_SHORT).show()
                    Log.d("회원가입", "$result")

                }

                override fun onFailure(call: Call<register>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "정보를 재확인해주세요." , Toast.LENGTH_SHORT).show()
                    Log.d("회원가입", "통신 실패")
                }
            })
            finish()
            Log.d("Register", "회원가입 버튼 클릭")
        }
    }
}