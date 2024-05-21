package com.example.storeapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updateLogin(newUserName:String, newPassword:String){
        _username.value = newUserName
        _password.value = newPassword
    }

    fun loginInit(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val result = loginUseCase.invoke(_username.value,_password.value)
                    Log.e("good",result.token)
                }catch (e:Exception){
                    Log.e("BAD", e.message.toString())
                }
            }
        }
    }
}