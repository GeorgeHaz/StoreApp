package com.example.storeapp.utils

import com.example.storeapp.model.LoginResponse

sealed class LoginState {
    data object Idle : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}