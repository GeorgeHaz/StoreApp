package com.example.storeapp.domain

import com.example.storeapp.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String) =
        loginRepository.getLogin(username, password)
}