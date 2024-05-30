package com.example.storeapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.storeapp.component.TopBarEnd
import com.example.storeapp.viewModel.SignUpViewModel

@Composable
fun SingUp(navController: NavController, signUpViewModel: SignUpViewModel) {
    Scaffold(
        topBar = {
            TopBarEnd(
                Text = { /*TODO*/ },
                IconView = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                },
                IconShow = true
            )
        }
    )
    {
        BodySing(modifier = Modifier.padding(paddingValues = it), signUpViewModel)
    }
}

@Composable
fun BodySing(modifier: Modifier, viewModel: SignUpViewModel) {
    val email by viewModel.email.collectAsState("")
    val username by viewModel.username.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val phone by viewModel.phone.collectAsState("")
    val firstName by viewModel.firstName.collectAsState("")
    val lastName by viewModel.lastName.collectAsState("")
    val city by viewModel.city.collectAsState("")
    val street by viewModel.street.collectAsState("")
    val number by viewModel.number.collectAsState(0)
    val zipCode by viewModel.zipCode.collectAsState("")
    val longitude by viewModel.longitude.collectAsState("")
    val latitude by viewModel.latitude.collectAsState("")

    Column(modifier = modifier) {
        Text(text = "Sing Up")
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onUserChange(it, username, password, phone) },
            label = {
                Text(text = "Email")
            })
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.onUserChange(email, it, password, phone) },
            label = {
                Text(text = "Username")
            })
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onUserChange(email, username, it, phone) },
            label = {
                Text(text = "Password")
            })
        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.onUserChange(email, username, password, it) },
            label = {
                Text(text = "Phone")
            })
        OutlinedTextField(
            value = firstName, 
            onValueChange = {viewModel.onNameChange(it, lastName)},
            label = { Text(text = "First Name")})
        OutlinedTextField(
            value = lastName,
            onValueChange = { viewModel.onNameChange(firstName, it) },
            label = { Text(text = "Last Name") })
        OutlinedTextField(
            value = city,
            onValueChange = {
                viewModel.onAddressChange(
                    it,
                    street,
                    number,
                    zipCode,
                )
            },
            label = { Text(text = "City") })
        OutlinedTextField(
            value = street,
            onValueChange = {viewModel.onAddressChange(
                city,
                it,
                number,
                zipCode)},
            label = {Text(text = "Street")})

        OutlinedTextField(
            value = number.toString(),
            onValueChange = {viewModel.onAddressChange(
                city,
                street,
                it.toInt(),
                zipCode)},
            label = {Text(text = "Number")})
    }
}