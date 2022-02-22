package com.example.capstone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton: Button = findViewById(R.id.regi_signupbutton)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://da86-125-180-55-163.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var registerService = retrofit.create(RetrofitRegister::class.java)
        var registerEmail = retrofit.create(RetrofitEmail::class.java)
        var registerKey = retrofit.create(RetrofitKey::class.java)

        var check: TextView = findViewById(R.id.regi_pwok)

        var pw: EditText = findViewById(R.id.regi_pw)
        var repw: EditText = findViewById(R.id.regi_repw)
        var id: EditText = findViewById(R.id.regi_id)
        var name: EditText = findViewById(R.id.regi_nickname)
        var telnumber: EditText = findViewById(R.id.regi_tel)
        var codeButton: Button = findViewById(R.id.regi_emailButton)
        var checkButton: Button = findViewById(R.id.regi_keyButton)
        var key: EditText = findViewById(R.id.regi_key)

        codeButton.setOnClickListener {

            val emailAdd = emailkey(id.text.toString())
            val email = id.text.toString()
            registerEmail.requestKey(email).enqueue(object : Callback<emailkey> {

                override fun onResponse(call: Call<emailkey>, response: Response<emailkey>) {
                    val result = response.body()
                    val code = response.code()
                    Toast.makeText(this@RegisterActivity, "이메일로 인증번호를 보냈습니다." , Toast.LENGTH_SHORT).show()
                    Log.d("메일인증", "성공, response.body = $result")
                }

                override fun onFailure(call: Call<emailkey>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "이메일을 재확인해주세요." , Toast.LENGTH_SHORT).show()
                    Log.d("메일인증", "통신 실패")
                }
            })
        }
        checkButton.setOnClickListener {
            val regikey = key.text.toString()

            registerKey.requestResponse(regikey).enqueue(object : Callback<code> {
                override fun onResponse(call: Call<code>, response: Response<code>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<code>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

        repw.addTextChangedListener(object : TextWatcher {
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

            registerService.RequestRegister(email, password, nickname).enqueue(object :
                Callback<register> {
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