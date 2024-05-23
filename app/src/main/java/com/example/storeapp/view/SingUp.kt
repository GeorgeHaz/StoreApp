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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.storeapp.component.AddUserLoggin
import com.example.storeapp.component.TopBarEnd

@Composable
fun SingUp(navController: NavController) {
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
        BodySing(modifier = Modifier.padding(paddingValues = it))
    }
}

@Composable
fun BodySing(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Sing Up")
        AddUserLoggin(Text = "email"){}
    }
}