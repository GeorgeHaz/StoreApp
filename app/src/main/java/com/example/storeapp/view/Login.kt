package com.example.storeapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storeapp.navigation.RouteNav
import com.example.storeapp.viewModel.LoginViewModel

@Composable
fun Login(loginViewModel: LoginViewModel, navController: NavController){
    Surface(modifier = Modifier.fillMaxSize()) {
        LoginBody(loginViewModel)
        FootBody(navController = navController)
    }
}

@Composable
fun LoginBody(loginViewModel: LoginViewModel){
    val username by loginViewModel.username.collectAsState("")
    val password by loginViewModel.password.collectAsState("")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =Modifier.fillMaxSize())
    {
        TextField(
            value = username,
            onValueChange = {loginViewModel.updateLogin(it,password)},
            label = { Text(text = "User") },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ))
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = password,
            onValueChange = {loginViewModel.updateLogin(username, it)},
            label = { Text(text = "Pass")},
            singleLine = true,
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = { loginViewModel.loginInit() }) {
            Text(text = "AGREE")
        }
    }
}

@Composable
fun FootBody(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ){
        Button(onClick = {
            navController.navigate(route = RouteNav.SigInUp.route)
        }, modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "SingUp")
        }
    }
}