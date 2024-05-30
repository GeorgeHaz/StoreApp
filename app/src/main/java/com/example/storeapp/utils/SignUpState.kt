package com.example.storeapp.utils

import com.example.storeapp.model.LoginResponse
import com.example.storeapp.model.SingResponse

sealed class SignUpState {
    data object Idle : SignUpState()
    data class Success(val response: SingResponse) : SignUpState()
    data class Error(val message: String) : SignUpState()
}