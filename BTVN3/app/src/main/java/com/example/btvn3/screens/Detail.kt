package com.example.btvn3.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.navigation.NavController

import com.example.btvn3.R

@Composable
fun btvn3(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.jetpack_compose),  // Đảm bảo hình ảnh tồn tại
            contentDescription = "logo",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(50.dp))

        Text("Jetpack Compose", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        Text(
            "Jetpack Compose is a modern UI toolkit for building " +
                    "native Android applications using a declarative programming approach.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(100.dp))

        Button(
            onClick = {
                navController.navigate("home")  // Đảm bảo có composable cho "home"
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(60.dp)
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.White
            )
        ) {
            Text("I'm ready")
        }
    }
}
