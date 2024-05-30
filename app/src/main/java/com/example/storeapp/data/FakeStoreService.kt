package com.example.storeapp.data

import com.example.storeapp.model.Address
import com.example.storeapp.model.LoginRequest
import com.example.storeapp.model.LoginResponse
import com.example.storeapp.model.Name
import com.example.storeapp.model.SingRequest
import com.example.storeapp.model.SingResponse
import javax.inject.Inject

class FakeStoreService @Inject constructor(private val apiService: ApiService) {

    suspend fun getLogin(username: String, password: String): LoginResponse{
        val response = apiService.getLogin(LoginRequest(username, password))
        return response
    }

    suspend fun getSingUp(email:String, username:String, password:String, name:Name, address:Address, phone:String): SingResponse {
        val response = apiService.getSingUp(SingRequest(email, username, password, name,address,phone))
        return response
    }
}