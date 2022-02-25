package com.smartherd.mvvm_simplifiedcoding.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartherd.mvvm_simplifiedcoding.R
import com.smartherd.mvvm_simplifiedcoding.data.db.entities.User
import com.smartherd.mvvm_simplifiedcoding.databinding.ActivityLoginBinding
import com.smartherd.mvvm_simplifiedcoding.utils.hide
import com.smartherd.mvvm_simplifiedcoding.utils.show
import com.smartherd.mvvm_simplifiedcoding.utils.snackbar
import com.smartherd.mvvm_simplifiedcoding.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        val binding :  ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {
        toast("Login Started")
        progress_bar.show()
    }

    override fun onSuccess(user : User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
//        toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
//        toast(message)
    }
}