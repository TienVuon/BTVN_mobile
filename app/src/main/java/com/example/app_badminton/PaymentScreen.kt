package com.example.app_badminton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PaymentScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Thanh toán đặt sân",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        Text("Vui lòng quét mã QR hoặc chuyển khoản theo thông tin bên dưới:")

        Spacer(Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.maqr1), // bạn thêm ảnh qr_payment.png vào res/drawable
            contentDescription = "QR Code Thanh toán",
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.height(16.dp))
        Text("Ngân hàng: Techcombank")
        Text("Số tài khoản: 16979999999999")
        Text("Chủ tài khoản: Nguyễn Tiến Vươn")
        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate("home") {
                popUpTo("payment") { inclusive = true }
            }
        }) {
            Text("Hoàn tất thanh toán")
        }
    }
}
