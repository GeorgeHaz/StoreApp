package com.example.storeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storeapp.view.Login
import com.example.storeapp.view.SingUp
import com.example.storeapp.viewModel.LoginViewModel

@Composable
fun NavManager(loginViewModel: LoginViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RouteNav.Login.route) {
        composable(RouteNav.Login.route) {
            Login(loginViewModel = loginViewModel, navController = navController)
        }
        composable(RouteNav.SigInUp.route){
            SingUp(navController = navController)
        }
    }
}