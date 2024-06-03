package com.example.storeapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storeapp.component.TopBarConf
import com.example.storeapp.navigation.RouteNav
import com.example.storeapp.utils.SignUpState
import com.example.storeapp.viewModel.SignUpViewModel

@Composable
fun SingUp(navController: NavController, signUpViewModel: SignUpViewModel) {
    Scaffold(
        topBar = {
            TopBarConf(
                text = "Sign Up",
                iconShow = true,
                iconDesc = "exit",
                navController = navController,
                iconImageVector = Icons.Filled.Close,
                backPage = true
            )
        }
    )
    {
        BodySing(modifier = Modifier.padding(paddingValues = it), signUpViewModel, navController)
    }
}

@Composable
fun BodySing(modifier: Modifier, signUpViewModel: SignUpViewModel, navController: NavController) {
    var currentStep by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentStep) {
            0 -> StepOne(signUpViewModel) { currentStep = 1 }
            1 -> StepTwo(signUpViewModel) { currentStep = 2 }
            2 -> StepThree(signUpViewModel) { currentStep = 3 }
            3 -> StepFour(signUpViewModel, navController = navController) { signUpViewModel.singUpInit() }
        }
    }

}

@Composable
fun StepOne(viewModel: SignUpViewModel, onNext: () -> Unit) {
    val email by viewModel.email.collectAsState("")
    val username by viewModel.username.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val phone by viewModel.phone.collectAsState("")
    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onUserChange(it, username, password, phone) },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
        )
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.onUserChange(email, it, password, phone) },
            label = {
                Text(text = "Username")
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onUserChange(email, username, it, phone) },
            label = {
                Text(text = "Password")
            },
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
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.onUserChange(email, username, password, it) },
            label = {
                Text(text = "Phone")
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "Next",
            modifier = Modifier
                .clickable { onNext() }
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StepTwo(viewModel: SignUpViewModel, onNext: () -> Unit) {

    val firstName by viewModel.firstName.collectAsState("")
    val lastName by viewModel.lastName.collectAsState("")

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { viewModel.onNameChange(it, lastName) },
            label = { Text(text = "First Name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { viewModel.onNameChange(firstName, it) },
            label = { Text(text = "Last Name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "Next",
            modifier = Modifier
                .clickable { onNext() }
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun StepThree(viewModel: SignUpViewModel, onNext: () -> Unit){
    val city by viewModel.city.collectAsState("")
    val street by viewModel.street.collectAsState("")
    val number by viewModel.number.collectAsState("")
    val zipCode by viewModel.zipCode.collectAsState("")

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
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
            label = { Text(text = "City") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = street,
            onValueChange = {
                viewModel.onAddressChange(
                    city,
                    it,
                    number,
                    zipCode
                )
            },
            label = { Text(text = "Street") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = number,
            onValueChange = {
                viewModel.onAddressChange(
                    city,
                    street,
                    it,
                    zipCode
                )
            },
            label = { Text(text = "Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        OutlinedTextField(
            value = zipCode,
            onValueChange = {
                viewModel.onAddressChange(
                    city,
                    street,
                    number,
                    it
                )
            },
            label = { Text(text = "ZipCode") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "Next",
            modifier = Modifier
                .clickable { onNext() }
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun StepFour(viewModel: SignUpViewModel, navController: NavController, submit: () -> Unit){

    val longitude by viewModel.longitude.collectAsState("")
    val latitude by viewModel.latitude.collectAsState("")

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = latitude,
            onValueChange = {
                viewModel.onLocationChange(
                    it,
                    longitude
                )
            },
            label = { Text(text = "Longitude") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )

        OutlinedTextField(
            value = longitude,
            onValueChange = {
                viewModel.onLocationChange(
                    latitude,
                    it
                )
            },
            label = { Text(text = "Latitude") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = { submit() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }

        val signUpState by viewModel.signUpState.collectAsState()

        when (signUpState) {
            is SignUpState.Idle -> {}
            is SignUpState.Success -> {
                navController.navigate(route = RouteNav.Login.route)
            }

            is SignUpState.Error -> Text(
                text = "Error: ${(signUpState as SignUpState.Error).message}",
                color = Color.Red
            )
        }
    }
}