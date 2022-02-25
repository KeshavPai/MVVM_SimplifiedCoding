package com.smartherd.mvvm_simplifiedcoding.data.network.responses

import com.smartherd.mvvm_simplifiedcoding.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)