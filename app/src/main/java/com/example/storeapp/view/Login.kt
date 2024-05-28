package com.example.storeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storeapp.R
import com.example.storeapp.navigation.RouteNav
import com.example.storeapp.utils.LoginState
import com.example.storeapp.viewModel.LoginViewModel

@Composable
fun Login(loginViewModel: LoginViewModel, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LoginBody(modifier = Modifier.fillMaxHeight(0.5f))
                FootBody(
                    loginViewModel, navController,
                    modifier = Modifier.fillMaxHeight(0.5f)
                )
            }
        }
    }
}

@Composable
fun LoginBody(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_fakestore),
            contentDescription = "app logo",
            modifier = Modifier
                .size(128.dp)
        )
    }
}

@Composable
fun FootBody(loginViewModel: LoginViewModel, navController: NavController, modifier: Modifier) {
    val username by loginViewModel.username.collectAsState("")
    val password by loginViewModel.password.collectAsState("")
    val loginState by loginViewModel.loginState.collectAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { loginViewModel.updateLogin(it, password) },
            label = { Text(text = "User") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { loginViewModel.updateLogin(username, it) },
            label = { Text(text = "Pass") },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "show password")
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        Text(
            text = "Forgot Password?",
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = { loginViewModel.loginInit() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "AGREE")
        }
        when (loginState) {
            is LoginState.Idle -> {}
            is LoginState.Success -> {
                navController.navigate(route = RouteNav.Home.route)
            }

            is LoginState.Error -> Text(
                text = "Error: ${(loginState as LoginState.Error).message}",
                color = Color.Red
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 10.sp
            )
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier
                    .clickable {
                        navController.navigate(RouteNav.SigInUp.route)
                    }
                    .padding(start = 8.dp)
            )
        }
    }
}