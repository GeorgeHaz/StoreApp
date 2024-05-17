package com.example.storeapp.navigation

sealed class RouteNav(val route:String, val name:String) {
    data object Login : RouteNav("Login", "Login")
    data object SigInUp  : RouteNav("SigInUp", "SigInUp")

}