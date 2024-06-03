package com.example.storeapp.model

data class SingRequest(
    val email: String,
    val username: String,
    val password: String,
    val name:Name,
    val address:Address,
    val phone:String
)

data class Name(
    val firstname:String,
    val lastname:String
)

data class Address(
    val city:String,
    val street:String,
    val number:String,
    val zipcode:String,
    val geoLocation:Geolocation
)

data class Geolocation(
    val lat:String,
    val long:String
)

data class SingResponse(
    val id:Int
)