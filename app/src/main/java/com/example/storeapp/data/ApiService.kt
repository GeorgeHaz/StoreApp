package com.example.storeapp.data

import com.example.storeapp.model.LoginRequest
import com.example.storeapp.model.LoginResponse
import com.example.storeapp.utils.Constans.Companion.LOGIN_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(LOGIN_URL)
    suspend fun getLogin(@Body request: LoginRequest): LoginResponse
}