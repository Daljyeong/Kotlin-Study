package com.example.week07.example1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen1(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )

        // A로 이동하는 버튼
        Button(onClick = {
            navController.navigate(Routes.A.route)
        }) {
            Text(text = "Screen A")
        }

        // B로 이동하는 버튼
        Button(onClick = {
            navController.navigate(Routes.B.route)
        }) {
            Text(text = "Screen B")
        }
    }
}
