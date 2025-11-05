package com.example.app_badminton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_badminton.data.BookingPreferences
import com.example.app_badminton.data.CartPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CourtBookingDetailScreen(
    navController: NavController,
    courtName: String
) {
    val context = LocalContext.current
    val bookingPrefs = remember { BookingPreferences(context) }
    val cartPrefs = remember { CartPreferences(context) }
    val scope = rememberCoroutineScope()

    var selectedDate by remember { mutableStateOf(getTodayDate()) }
    var selectedTime by remember { mutableStateOf("") }
    var bookedSlots by remember { mutableStateOf(listOf<String>()) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    val timeSlots = listOf(
        "06:00-07:00", "07:00-08:00", "08:00-09:00",
        "09:00-10:00", "10:00-11:00", "11:00-12:00",
        "13:00-14:00", "14:00-15:00", "15:00-16:00",
        "16:00-17:00", "17:00-18:00", "18:00-19:00",
        "19:00-20:00", "20:00-21:00", "21:00-22:00"
    )

    // Load khung giờ đã đặt
    LaunchedEffect(selectedDate) {
        bookedSlots = bookingPrefs.getBookedSlots(courtName, selectedDate)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Đặt lịch - $courtName",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(12.dp))
        Text("Ngày: $selectedDate", fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(timeSlots) { slot ->
                val isBooked = bookedSlots.contains(slot)
                val backgroundColor = when {
                    isBooked -> Color.LightGray
                    selectedTime == slot -> Color(0xFF009688)
                    else -> Color.White
                }

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .background(backgroundColor)
                        .clickable(enabled = !isBooked) { selectedTime = slot }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(slot, color = if (isBooked) Color.Gray else Color.Black)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { showConfirmDialog = true },
            enabled = selectedTime.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Đặt lịch ngay")
        }
    }

    // Hộp thoại xác nhận & thanh toán
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Xác nhận đặt sân") },
            text = {
                Column {
                    Text("🏸 Sân: $courtName")
                    Text("📅 Ngày: $selectedDate")
                    Text("⏰ Giờ: $selectedTime")
                    Text("💵 Giá: 100.000đ")
                }
            },
            confirmButton = {
                Button(onClick = {
                    scope.launch {
                        bookingPrefs.saveBooking(courtName, selectedDate, selectedTime)
                        cartPrefs.addToCart(courtName, selectedDate, selectedTime, 100000)
                    }
                    showConfirmDialog = false
                    navController.navigate("payment") // chuyển sang trang thanh toán
                }) {
                    Text("Thanh toán")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}

fun getTodayDate(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date())
}
