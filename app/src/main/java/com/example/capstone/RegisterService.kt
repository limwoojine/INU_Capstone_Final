package com.example.capstone

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.MutableLiveData as MutableLiveData1

class RegisterService {

    private val BASE_URL = "https://da86-125-180-55-163.ngrok.io/"
    private val client : RetrofitEmail

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        client = retrofit.create(RetrofitEmail::class.java)
    }

    fun postEmail(email:String) : LiveData<String> {

        val responseEmail : MutableLiveData1<String> = MutableLiveData1()
        val body = emailkey(email)
        val call = client.postEmail(body)

        call.enqueue(object: Callback<emailResponse> {
            override fun onFailure(call: Call<emailResponse>, t: Throwable) {
                Log.e("이메일 error", "${t.message}")
            }

            override fun onResponse(
                call: Call<emailResponse>,
                response: Response<emailResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("요청 성공!", "onResponse is Successful!")
                    val body = response.body()
                    if(body?.response == email){
                        Log.d("이메일 인증번호 전송 성공!", "onResponse is Successful!")
                        responseEmail.value = body?.response
                    }
                }
                else {
                    Log.e("이메일 인증번호 전송 실패", "이미가입한 이메일")
                    responseEmail.value = "registerer email"
                }
            }
        })
        return responseEmail
    }

    fun isEmailVerified(email: String, code:String) : LiveData<String> {

        val responseCode : MutableLiveData1<String> = MutableLiveData1()
        val body = VerifyCode(code)
        val call = client.postVerifyCode(body,email)

        call.enqueue(object: Callback<emailResponse> {
            override fun onFailure(call: Call<emailResponse>, t: Throwable) {
                Log.e("인증번호 불일치 error", "${t.message}")
            }

            override fun onResponse(
                call: Call<emailResponse>,
                response: Response<emailResponse>
            ) {
                Log.d("요청 성공!", "onResponse is Successful!")
                if(response.isSuccessful){
                    val body = response.body()
                    if(body?.response == code) {
                        Log.d("인증번호 일치! 이메일 인증 성공!", "onResponse is Successful!")
                        responseCode.value = body?.response
                    }
                }
                else {
                    Log.e("인증번호 불일치. 이메일 인증 실패", "response is not Successful")
                    responseCode.value = "code is incorrect"
                }
            }
        })
        return responseCode
    }

    fun registerMember(email: String, nickName: String, password: String){

        val body = register(email,nickName,password)
        val call = client.registerMember(body)
        var id: Int? = null

        call.enqueue(object: Callback<MemberResponse> {
            override fun onFailure(call: Call<MemberResponse>, t: Throwable) {
                Log.e("회원정보 전송 error", "${t.message}")
            }

            override fun onResponse(
                call: Call<MemberResponse>,
                response: Response<MemberResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("회원정보 전송 성공!", "onResponse is Successful!")
                    val body = response.body()
                    id = body?.id
                    Log.d("회원 ID :", id.toString())
                }
                else {
                    Log.e("회원정보 전송 실패", "response is not Successful")
                }
            }
        })
    }
}