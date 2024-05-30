package com.example.storeapp.domain

import com.example.storeapp.model.Address
import com.example.storeapp.model.Name
import com.example.storeapp.repository.SingUpRepository
import javax.inject.Inject

class SingUpUseCase @Inject constructor(private val repository: SingUpRepository) {

    suspend fun invoke(
        email: String,
        username: String,
        password: String,
        name: Name,
        address: Address,
        phone: String
    ) =
        repository.signUp(email, username, password, name, address, phone)

}