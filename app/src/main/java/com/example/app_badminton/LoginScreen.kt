package com.example.app_badminton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_badminton.data.UserPreferences
import kotlinx.coroutines.launch

// --- Định nghĩa Màu sắc Mới (Inspired by Badminton Energy) ---
object LoginScreen {
    val PrimaryGreen = Color(0xFF4CAF50) // Xanh lá tươi
    val AccentBlue = Color(0xFF1976D2)   // Xanh dương đậm
    val LightBackground = Color(0xFFF5F5F5) // Nền nhẹ
    val CardBackground = Color.White        // Nền thẻ
    val ShadowColor = Color(0x33000000)     // Bóng đổ nhẹ
}

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginScreen.LightBackground), // Nền nhẹ
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                // Card Nền Trắng với Bo Góc và Bóng Đổ
                .clip(RoundedCornerShape(16.dp))
                .background(LoginScreen.CardBackground)
                .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            // 🏸 Tiêu đề (Lớn hơn, màu Primary)
            Text(
                text = "BADMINTON UTH",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = LoginScreen.PrimaryGreen
            )
            Text(
                text = "ĐĂNG NHẬP",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Ô nhập tên đăng nhập
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Tên đăng nhập") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp), // Bo góc cho Input
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LoginScreen.AccentBlue,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = LoginScreen.AccentBlue,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ô nhập mật khẩu
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp), // Bo góc cho Input
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LoginScreen.AccentBlue,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = LoginScreen.AccentBlue,
                )
            )

            // Liên kết quên mật khẩu (chuyển lên trên nút Đăng nhập)
            Box(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = "Quên mật khẩu?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        message = "Tính năng đang được phát triển"
                    }
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Nút đăng nhập (Màu Primary, Bo góc, Chiều cao lớn)
            Button(
                onClick = {
                    scope.launch {
                        try {
                            val success = userPrefs.validateUser(username, password)
                            if (success) {
                                message = ""
                                // Chuyển sang Home khi đăng nhập đúng
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                message = "Sai tên đăng nhập hoặc mật khẩu!"
                            }
                        } catch (e: Exception) {
                            message = "Lỗi khi đăng nhập: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp), // Tăng chiều cao
                colors = ButtonDefaults.buttonColors(containerColor = LoginScreen.PrimaryGreen),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp) // Thêm bóng đổ
            ) {
                Text("ĐĂNG NHẬP", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            // Hiển thị thông báo lỗi
            if (message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Liên kết chuyển sang đăng ký (Màu Accent)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chưa có tài khoản? ",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
                Text(
                    text = "Đăng ký ngay",
                    color = LoginScreen.AccentBlue, // Màu nhấn mạnh
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }
        }
    }
}