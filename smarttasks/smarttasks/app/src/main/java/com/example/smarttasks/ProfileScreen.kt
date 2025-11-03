package com.example.smarttasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser

    // Nếu người dùng đã đăng nhập (user != null), hiển thị thông tin
    if (user != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hiển thị ảnh đại diện
            user.photoUrl?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(16.dp))

            // Hiển thị tên và email
            Text(text = user.displayName ?: "No Name")
            Text(text = user.email ?: "No Email")
            Spacer(Modifier.height(20.dp))

            // Nút Đăng xuất
            Button(onClick = {
                FirebaseAuth.getInstance().signOut() // Đăng xuất người dùng
                onLogout() // Quay lại màn hình login
            }) {
                Text("Đăng xuất")
            }
        }
    } else {
        // Nếu không có user (chưa đăng nhập), yêu cầu đăng nhập lại
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Chưa đăng nhập. Vui lòng đăng nhập.")
        }
    }
}
