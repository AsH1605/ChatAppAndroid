package com.cookie.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.cookie.chatapp.presentation.login.LoginVM
import com.cookie.chatapp.presentation.login.UserLoginScreen
import com.cookie.chatapp.presentation.register.UserRegistrationScreen
import com.cookie.chatapp.presentation.register.UserVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userVM: LoginVM by viewModels()
        enableEdgeToEdge()
        setContent {
            UserLoginScreen(userVM)
        }
    }
}

