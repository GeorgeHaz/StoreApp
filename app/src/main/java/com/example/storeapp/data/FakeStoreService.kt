package com.example.storeapp.data

import com.example.storeapp.model.LoginRequest
import com.example.storeapp.model.LoginResponse
import javax.inject.Inject

class FakeStoreService @Inject constructor(private val apiService: ApiService) {

    suspend fun getLogin(username: String, password: String): LoginResponse{
        val response = apiService.getLogin(LoginRequest(username, password))
        return response
    }
}