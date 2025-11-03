package com.example.btvn3.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import java.util.Date


// Dialog Screen
@Composable
fun Dialog(navController: NavController) {
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "DIALOG",
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
                        Text("AlertDialog", style = MaterialTheme.typography.bodyLarge)
                        Text("Hộp thoại cảnh báo", color = Color.Gray)
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
                        Text("DatePickerDialog", style = MaterialTheme.typography.bodyLarge)
                        Text("Chọn ngày", color = Color.Gray)
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
                        Text("TimePickerDialog", style = MaterialTheme.typography.bodyLarge)
                        Text("Chọn giờ", color = Color.Gray)
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
                        "AlertDialog",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            var showDialog by remember { mutableStateOf(false) }

            Button(onClick = { showDialog = true }) {
                Text("Show AlertDialog")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Alert Dialog") },
                    text = { Text("This is an alert dialog example") },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
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
                        "DatePickerDialog",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            var showDatePicker by remember { mutableStateOf(false) }
            var selectedDate by remember { mutableStateOf("No date selected") }

            Button(onClick = { showDatePicker = true }) {
                Text("Show Date Picker")
            }

            Text("Selected: $selectedDate", modifier = Modifier.padding(16.dp))

            if (showDatePicker) {
                AlertDialog(
                    onDismissRequest = { showDatePicker = false },
                    title = { Text("Date Picker") },
                    text = { Text("Date picker dialog would appear here") },
                    confirmButton = {
                        Button(onClick = {
                            selectedDate = Date().toString()
                            showDatePicker = false
                        }) {
                            Text("Select Today")
                        }
                    }
                )
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
                        "TimePickerDialog",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            var showTimePicker by remember { mutableStateOf(false) }
            var selectedTime by remember { mutableStateOf("No time selected") }

            Button(onClick = { showTimePicker = true }) {
                Text("Show Time Picker")
            }

            Text("Selected: $selectedTime", modifier = Modifier.padding(16.dp))

            if (showTimePicker) {
                AlertDialog(
                    onDismissRequest = { showTimePicker = false },
                    title = { Text("Time Picker") },
                    text = { Text("Time picker dialog would appear here") },
                    confirmButton = {
                        Button(onClick = {
                            selectedTime = "12:00 PM"
                            showTimePicker = false
                        }) {
                            Text("Select Noon")
                        }
                    }
                )
            }
        }
    }
}