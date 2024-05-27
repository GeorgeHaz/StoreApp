package com.example.storeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storeapp.R
import com.example.storeapp.navigation.RouteNav
import com.example.storeapp.utils.LoginState
import com.example.storeapp.viewModel.LoginViewModel

@Composable
fun Login(loginViewModel: LoginViewModel, navController: NavController){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                LoginBody(loginViewModel, navController)
            }
            FootBody(
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun LoginBody(loginViewModel: LoginViewModel, navController: NavController){
    val username by loginViewModel.username.collectAsState("")
    val password by loginViewModel.password.collectAsState("")
    val loginState by loginViewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_fakestore),
            contentDescription ="app logo",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 32.dp)
        )
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
        when(loginState){
            is LoginState.Idle -> {}
            is LoginState.Success -> {
                navController.navigate(route = RouteNav.Home.route)
            }
            is LoginState.Error -> Text(
                text = "Error: ${(loginState as LoginState.Error).message}",
                color = Color.Red)
        }
    }
}

@Composable
fun FootBody(navController: NavController, modifier: Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ){
        Button(onClick = {
            navController.navigate(route = RouteNav.SigInUp.route)
        }
        ) {
            Text(text = "Sing Up")
        }
    }
}