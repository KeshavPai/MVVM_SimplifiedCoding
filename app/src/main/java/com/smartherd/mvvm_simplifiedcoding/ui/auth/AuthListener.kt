package com.smartherd.mvvm_simplifiedcoding.ui.auth

import androidx.lifecycle.LiveData
import com.smartherd.mvvm_simplifiedcoding.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user : User)

    fun onFailure(message : String)
}