package com.smartherd.mvvm_simplifiedcoding.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartherd.mvvm_simplifiedcoding.data.db.AppDatabase
import com.smartherd.mvvm_simplifiedcoding.data.db.entities.User
import com.smartherd.mvvm_simplifiedcoding.data.network.MyAPI
import com.smartherd.mvvm_simplifiedcoding.data.network.SafeApiRequest
import com.smartherd.mvvm_simplifiedcoding.data.network.responses.AuthResponse
import com.smartherd.mvvm_simplifiedcoding.utils.ApiExceptions
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api : MyAPI,
    private val db : AppDatabase
    ) : SafeApiRequest() {

    suspend fun userLogin(email : String, password : String) : AuthResponse {
        return apiRequest { MyAPI().userLogin(email, password) }
    }

    suspend fun saveUser(user : User) = db.getUserDao().insert(user)

    /*suspend fun userLogin(email : String, password : String) : AuthResponse {
        return MyAPI().userLogin(email, password)

        val loginResponse = MutableLiveData<String>()
        MyAPI().userLogin(email, password)
            .enqueue(object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.toString()
                    } else {
                        loginResponse.value = response.errorBody()?.toString()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }
            })

        return loginResponse
    }*/
}