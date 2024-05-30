package com.example.storeapp.repository

import com.example.storeapp.data.FakeStoreService
import com.example.storeapp.model.Address
import com.example.storeapp.model.Name
import javax.inject.Inject

class SingUpRepository @Inject constructor(private val apiService: FakeStoreService) {
    suspend fun signUp(
        email: String,
        username: String,
        password: String,
        name: Name,
        address: Address,
        phone: String
    ) =
        apiService.getSingUp(email, username, password, name, address, phone)

}