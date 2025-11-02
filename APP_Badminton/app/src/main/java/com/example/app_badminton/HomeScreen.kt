package com.example.app_badminton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- Định nghĩa màu sắc và gradient mới ---
val PrimaryColor = Color(0xFF4CAF50)
val AccentColor = Color(0xFFFF9800)
val SuperAccentColor = Color(0xFFF44336)
val DarkTextColor = Color(0xFF212121)
val LightGreyBackground = Color(0xFFF7F7F7)
val CardBackgroundColor = Color(0xFFFFFFFF)
val GradientCTA = Brush.horizontalGradient(
    colors = listOf(Color(0xFFFFB300), Color(0xFFFF9800))
)
val GradientHeader = Brush.verticalGradient(
    colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent)
)

@Composable
fun HomeScreen(navController: NavController) {
    val imageList = listOf(
        // Giả định tên sân không quá dài để phù hợp với cỡ chữ 20sp
        Triple(R.drawable.caulong1, "Sân Trong Nhà Đẳng Cấp", "300m - 4.8 ⭐"),
        Triple(R.drawable.caulong2, "Sân View Cực Chill", "500m - 4.5 ⭐"),
        Triple(R.drawable.caulong3, "Sân Đôi Cao Cấp VIP", "200m - 4.9 ⭐"),
        Triple(R.drawable.caulong4, "Sân Phổ Biến Nhất Tuần", "1km - 4.7 ⭐"),
        Triple(R.drawable.caulong5, "Khu Vực Giải Lao Đầy Đủ", "150m - 4.6 ⭐"),
        Triple(R.drawable.caulong6, "Sân Tập Luyện Chuyên Nghiệp", "800m - 4.4 ⭐")
    )

    val listState = rememberLazyListState()
    val scrollOffset = listState.firstVisibleItemScrollOffset

    Scaffold(
        // bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(LightGreyBackground),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- 1. Header Độc Đáo và Năng Động với Parallax Effect ---
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.caulong2),
                        contentDescription = "Banner Sân Cầu Lông",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationY = -scrollOffset * 0.5f
                            }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GradientHeader)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "BADMINTON UTH",
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            fontSize = 38.sp, // Giảm từ 42sp xuống 38sp để tránh tràn trên màn hình nhỏ
                            maxLines = 1,
                            overflow = TextOverflow.Clip // Đảm bảo không tràn
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Nâng tầm đam mê, bứt phá mọi giới hạn!",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 18.sp, // Giảm từ 20sp xuống 18sp
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis // Thêm dấu ... nếu tràn
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // --- 2. Nút CTA Đặt Lịch Ngay (Tối ưu hóa nội dung) ---
            item {
                Button(
                    onClick = { navController.navigate("booking") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp) // Giảm chiều cao nút xuống 60dp để tinh gọn hơn
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GradientCTA, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                // Rút gọn chữ để tránh tràn ngang
                                "ĐẶT SÂN LIỀN TAY",
                                fontSize = 20.sp, // Giảm từ 22sp xuống 20sp
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Clip
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Đặt lịch",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp) // Giảm size icon
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp)) // Giảm khoảng cách
            }

            // --- 3. Tiêu đề Khu vực Hình ảnh Sân (Tối ưu hóa) ---
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        // Tiêu đề nổi bật: Rút gọn text
                        text = "🏆 SÂN ĐƯỢC YÊU THÍCH NHẤT 🏆",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp, // Giảm từ 26sp xuống 22sp
                        color = SuperAccentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis // Thêm dấu ... nếu vẫn bị tràn
                    )
                }
                Spacer(modifier = Modifier.height(16.dp)) // Giảm khoảng cách
            }

            // --- 4. Danh sách Hình ảnh Sân ---
            items(imageList) { (imgId, name, details) ->
                SimplifiedCourtCard(imgId = imgId, name = name, details = details, navController = navController)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// --- Component Card Sân Cầu Lông Đã Tinh Giản (Tối ưu hóa) ---
@Composable
fun SimplifiedCourtCard(imgId: Int, name: String, details: String, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp)) // Giảm bo góc nhẹ
            .clickable { navController.navigate("court_detail/$name") },
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(8.dp) // Giảm đổ bóng nhẹ
    ) {
        Column {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Giảm chiều cao ảnh xuống 200dp
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(12.dp) // Giảm padding nội dung Card
            ) {
                Text(
                    text = name,
                    color = DarkTextColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp, // Giảm từ 20sp xuống 18sp
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // **Quan trọng: Thêm dấu ... nếu tên quá dài**
                )
                Spacer(modifier = Modifier.height(6.dp)) // Giảm khoảng cách
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nhóm thông tin Vị trí và Đánh giá (Đảm bảo không tràn)

                    // Khoảng cách
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Khoảng cách",
                        tint = PrimaryColor,
                        modifier = Modifier.size(16.dp) // Giảm size icon
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = details.substringBefore(" - "),
                        color = Color.Gray,
                        fontSize = 13.sp // Giảm cỡ chữ
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    // Đánh giá
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Đánh giá",
                        tint = AccentColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = details.substringAfter(" - "),
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}