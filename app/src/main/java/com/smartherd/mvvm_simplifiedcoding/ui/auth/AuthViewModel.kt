package com.smartherd.mvvm_simplifiedcoding.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.smartherd.mvvm_simplifiedcoding.data.repository.UserRepository
import com.smartherd.mvvm_simplifiedcoding.utils.ApiExceptions
import com.smartherd.mvvm_simplifiedcoding.utils.Coroutines

class AuthViewModel(
    private val repository : UserRepository
) : ViewModel() {

    var email : String? = null
    var password : String? = null

    var authListener : AuthListener? = null

    fun onLoginButtonClicked(view : View) {
        authListener?.onStarted()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // Show error message
            authListener?.onFailure("Invalid Email or Password")
            return
        }

        /*val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)*/

        /*val response = UserRepository().userLogin(email!!, password!!)
           if(response.isSuccessful) {
               authListener?.onSuccess(response.body()?.user!!)
           } else {
               authListener?.onFailure("Error Code : ${response.code()}")
           }*/

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e : ApiExceptions) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}