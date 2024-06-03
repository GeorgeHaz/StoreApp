package com.example.storeapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBarConf(
    text:String,
    iconShow:Boolean,
    iconDesc:String,
    navController: NavController,
    iconImageVector: ImageVector,
    backPage:Boolean,
    routeNav: String = ""
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)){
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 18.sp)
        if(iconShow){
            IconButton(onClick = {
                if(backPage)
                    navController.popBackStack()
                else
                navController.navigate(route = routeNav)
            },modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(imageVector = iconImageVector, contentDescription = iconDesc)
            }
        }
    }
}
