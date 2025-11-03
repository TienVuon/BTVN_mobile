package com.example.btvn3.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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


// List Screen
@Composable
fun List_(navController: NavController) {
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "LIST",
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
                        Text("LazyColumn", style = MaterialTheme.typography.bodyLarge)
                        Text("Danh sách dọc", color = Color.Gray)
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
                        Text("LazyRow", style = MaterialTheme.typography.bodyLarge)
                        Text("Danh sách ngang", color = Color.Gray)
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
                        Text("Grid Layout", style = MaterialTheme.typography.bodyLarge)
                        Text("Lưới", color = Color.Gray)
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
                        "LazyColumn",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LazyColumn(modifier = Modifier.height(200.dp)) {
                items(50) { index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Item $index", modifier = Modifier.padding(16.dp))
                    }
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
                        "LazyRow",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LazyRow(modifier = Modifier.height(80.dp)) {
                items(20) { index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .width(100.dp)
                    ) {
                        Text("Item $index", modifier = Modifier.padding(16.dp))
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
                        "Grid Layout",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.height(200.dp)) {
                items(30) { index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .aspectRatio(1f)
                    ) {
                        Text("$index", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}