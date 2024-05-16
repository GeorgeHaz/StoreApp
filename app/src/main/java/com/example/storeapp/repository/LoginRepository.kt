package com.example.storeapp.repository

import com.example.storeapp.data.FakeStoreService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val fakeStoreService: FakeStoreService) {
    suspend fun getLogin(username: String, password: String) = fakeStoreService.getLogin(username, password)
}