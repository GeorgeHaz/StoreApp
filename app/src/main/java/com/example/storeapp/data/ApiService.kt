package com.example.storeapp.data

import com.example.storeapp.model.LoginRequest
import com.example.storeapp.model.LoginResponse
import com.example.storeapp.model.SingRequest
import com.example.storeapp.model.SingResponse
import com.example.storeapp.utils.Constans.Companion.LOGIN_URL
import com.example.storeapp.utils.Constans.Companion.REGISTER_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(LOGIN_URL)
    suspend fun getLogin(@Body request: LoginRequest): LoginResponse

    @POST(REGISTER_URL)
    suspend fun getSingUp(@Body request: SingRequest): SingResponse
}