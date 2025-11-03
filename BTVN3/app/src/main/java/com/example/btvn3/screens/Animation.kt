package com.example.btvn3.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.draw.scale

// Animation Screen
@Composable
fun Animation(navController: NavController) {
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "ANIMATION",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            modifier = Modifier.clickable { navController.navigate("home") }
        )

        if (flag == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 1 },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Animated Visibility", style = MaterialTheme.typography.bodyLarge)
                        Text("Hiện/ẩn có hiệu ứng", color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 2 },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Crossfade", style = MaterialTheme.typography.bodyLarge)
                        Text("Chuyển cảnh", color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 3 },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Simple Animations", style = MaterialTheme.typography.bodyLarge)
                        Text("Hiệu ứng đơn giản", color = Color.Gray)
                    }
                }
            }
        } else if (flag == 1) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Animated Visibility",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            var visible by remember { mutableStateOf(true) }

            Button(onClick = { visible = !visible }) {
                Text("Toggle Visibility")
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Card(modifier = Modifier.padding(16.dp)) {
                    Text("Hello Animation!", modifier = Modifier.padding(16.dp))
                }
            }

        } else if (flag == 2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Crossfade",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            var currentScreen by remember { mutableStateOf("Screen A") }

            Button(onClick = {
                currentScreen = if (currentScreen == "Screen A") "Screen B" else "Screen A"
            }) {
                Text("Switch Screen")
            }

            Crossfade(targetState = currentScreen) { screen ->
                when (screen) {
                    "Screen A" -> Card(modifier = Modifier.padding(16.dp)) {
                        Text("Screen A Content", modifier = Modifier.padding(16.dp))
                    }

                    "Screen B" -> Card(modifier = Modifier.padding(16.dp)) {
                        Text("Screen B Content", modifier = Modifier.padding(16.dp))
                    }
                }
            }

        } else if (flag == 3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Simple Animations",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            val infiniteTransition = rememberInfiniteTransition()
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 1.5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .scale(scale)
            ) {
                Text("Pulse Animation", modifier = Modifier.padding(16.dp))
            }
        }
    }
}