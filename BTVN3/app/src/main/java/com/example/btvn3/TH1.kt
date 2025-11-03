package com.example.btvn3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color


@Composable
fun TH1() {
    var isError by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // CĂN GIỮA NGANG
        verticalArrangement = Arrangement.Center            // CĂN GIỮA DỌC
    ) {
        Text("My First App", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        if (!isError) {
            Text("Hello", style = MaterialTheme.typography.headlineSmall)
        } else {
            Text("I'm Nguyen Van A", style = MaterialTheme.typography.headlineSmall)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                isError = !isError
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
            Text("Say Hi!")
        }

    }
}