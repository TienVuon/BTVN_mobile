package com.example.btvn3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import java.util.*

// Navigation Screen
@Composable
fun Navigation(navController: NavController) {
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "NAVIGATION",
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
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Bottom Navigation", style = MaterialTheme.typography.bodyLarge)
                        Text("Thanh điều hướng dưới", color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 2 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Navigation Drawer", style = MaterialTheme.typography.bodyLarge)
                        Text("Menu trượt từ cạnh", color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 3 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Tab Layout", style = MaterialTheme.typography.bodyLarge)
                        Text("Chuyển tab ngang", color = Color.Gray)
                    }
                }
            }
        } else if (flag == 1) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Bottom Navigation",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("Bottom Navigation Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf("Home", "Search", "Profile")
                val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Person)

                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(icons[index], contentDescription = item) },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }

                Text("Selected: ${items[selectedItem]}", modifier = Modifier.padding(16.dp))
            }

        } else if (flag == 2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Navigation Drawer",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("Drawer Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                var showDrawer by remember { mutableStateOf(false) }

                Button(onClick = { showDrawer = true }) {
                    Text("Open Drawer")
                }

                if (showDrawer) {
                    AlertDialog(
                        onDismissRequest = { showDrawer = false },
                        title = { Text("Navigation Drawer") },
                        text = {
                            Column {
                                Text("• Home")
                                Text("• Settings")
                                Text("• Profile")
                            }
                        },
                        confirmButton = {
                            Button(onClick = { showDrawer = false }) {
                                Text("Close")
                            }
                        }
                    )
                }
            }

        } else if (flag == 3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB0E3F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Tab Layout",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("Tab Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                var selectedTab by remember { mutableStateOf(0) }
                val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index }
                        )
                    }
                }

                Text("Content for ${tabs[selectedTab]}", modifier = Modifier.padding(16.dp))
            }
        }
    }
}