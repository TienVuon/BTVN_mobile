package com.example.btvn3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavController

@Composable
fun Layout(navController: NavController) {
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "LAYOUT",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            modifier = Modifier.clickable {
                navController.navigate("home")
            }
        )

        if (flag == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Column
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 1 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Column",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Sắp xếp theo chiều dọc",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }

                // Row
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 2 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Row",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Sắp xếp theo chiều ngang",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }

                // Box
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 3 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Box",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Xếp chồng các phần tử",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }

                // Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 4 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Card",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Thẻ với elevation và shadow",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        } else if (flag == 1) {
            // Column Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Column",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Basic Column",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Column với khoảng cách",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Column căn giữa",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }
            }

        } else if (flag == 2) {
            // Row Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Row",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Basic Row",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Row với khoảng cách",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Row căn giữa",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green))
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue))
                }
            }

        } else if (flag == 3) {
            // Box Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Box",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Basic Box - Xếp chồng",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier.size(100.dp)) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Red)
                            .align(Alignment.TopStart)
                    )
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Green)
                            .align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Blue)
                            .align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Box với contentAlignment",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Box với text và icon",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color(0xFFE3F2FD)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(40.dp)
                        )
                        Text("Box Content")
                    }
                }
            }

        } else if (flag == 4) {
            // Card Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Card",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Basic Card",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Card Title", fontWeight = FontWeight.Bold)
                        Text("This is a basic card example")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Card với màu sắc",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E8)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Green Card", fontWeight = FontWeight.Bold)
                        Text("Card với background color")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Card với hình ảnh và button",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF2196F3))
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Featured Card", fontWeight = FontWeight.Bold)
                            Text("Card với header và actions")
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { }) {
                                Text("Action Button")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Card clickable",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Handle click */ },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.TouchApp, contentDescription = "Clickable")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Clickable Card")
                    }
                }
            }
        }
    }
}