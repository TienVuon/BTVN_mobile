package com.example.app_badminton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- Định nghĩa màu sắc và gradient ---
val PrimaryColor = Color(0xFF4CAF50)
val AccentColor = Color(0xFFFF9800)
val SuperAccentColor = Color(0xFFF44336)
val DarkTextColor = Color(0xFF212121)
val LightGreyBackground = Color(0xFFF7F7F7)
val CardBackgroundColor = Color(0xFFFFFFFF)
val ActiveTagColor = Color(0xFF1976D2)
val GradientCTA = Brush.horizontalGradient(
    colors = listOf(PrimaryColor, Color(0xFF8BC34A))
)

@Composable
fun HomeScreen(navController: NavController) {

    // --- Giả định CourtList và R.drawable đã được khai báo/import chính xác ---
    val courtList = listOf(
        Triple(R.drawable.caulong1, "Sân Trong Nhà Đẳng Cấp A1", "300m - 4.8 ⭐"),
        Triple(R.drawable.caulong2, "Sân View Cực Chill Thường", "500m - 4.5 ⭐"),
        Triple(R.drawable.caulong3, "Sân Đôi Cao Cấp VIP 99", "200m - 4.9 ⭐"),
        Triple(R.drawable.caulong4, "Sân Phổ Biến Nhất Tuần", "1km - 4.7 ⭐"),
        Triple(R.drawable.caulong5, "Khu Vực Giải Lao Đầy Đủ", "150m - 4.6 ⭐"),
        Triple(R.drawable.caulong6, "Sân Tập Luyện Chuyên Nghiệp", "800m - 4.4 ⭐")
    )
    val categories = listOf("Gần Nhất", "Được Đánh Giá Cao", "Giá Tốt", "Sân Trong Nhà", "24/7")

    Scaffold(
        // bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(LightGreyBackground),
            contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --- 1. Header Đặt Lịch Nhanh (Giao diện Pro) ---
            item {
                QuickBookingHeader(navController = navController)
            }

            // ✅ ĐIỀU CHỈNH: Giảm khoảng cách để các mục bắt đầu cao hơn một chút
            item {
                Spacer(modifier = Modifier.height(0.dp))
            }

            // --- 2. Thanh Danh mục/Lọc Nhanh (LazyRow) ---
            item {
                Text(
                    text = "Khám phá sân cầu lông",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextColor,
                    textAlign = TextAlign.Start, // ✅ Căn về phía trái nhìn hiện đại hơn
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(category = category, isActive = category == "Gần Nhất", navController = navController)
                    }
                }
            }

            // --- 3. Tiêu đề Khu vực Sân Đặc Sắc ---
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "🔥 Sân Nổi Bật Hôm Nay",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = SuperAccentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Xem tất cả",
                        color = PrimaryColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { /* Handle See All */ }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // --- 4. Danh sách Sân (Horizontal Card Layout) ---
            items(courtList) { (imgId, name, details) ->
                HorizontalCourtCard(imgId = imgId, name = name, details = details, navController = navController)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// ----------------------------------------------------------------------
// --- CÁC COMPONENT ĐÃ CẬP NHẬT ---
// ----------------------------------------------------------------------

/**
 * Header Đặt Lịch Nhanh: Đã thu gọn và thay đổi hình dạng/vị trí của các phần tử.
 */
@Composable
fun QuickBookingHeader(navController: NavController) {
    Box( // Dùng Box để xếp chồng background và content
        modifier = Modifier
            .fillMaxWidth()
            // ✅ ĐIỀU CHỈNH: Giảm padding bottom và chỉ bo góc dưới nhẹ
            .padding(bottom = 0.dp)
    ) {
        // --- Background (Khối màu xanh) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // ✅ THAY ĐỔI 1: GIẢM CHIỀU CAO của khối màu xanh
                .height(120.dp)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(PrimaryColor)
        ) {}

        // --- Content (Tiêu đề, Icon, Nút CTA) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // --- Phần Tiêu đề Logo/Thông tin ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // ✅ THAY ĐỔI 2: Dùng padding top cố định cho header
                    .padding(top = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. ICON DANH MỤC (MENU)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                        .clickable { navController.navigate("menu_drawer") },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // 2. TÊN APP (ĐÃ CĂN GIỮA)
                Text(
                    text = "BADMINTON UTH",
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                )

                // 3. ICON PROFILE
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                        .clickable { navController.navigate("profile") },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // ✅ THAY ĐỔI 3: Dời nút CTA và Tagline LÊN CAO hơn,
            // và sử dụng Card để làm nổi bật chúng khỏi nền
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp) // Nổi bật hơn
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardBackgroundColor) // Nền trắng cho Card
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // --- Nút CTA Lớn ---
                    Button(
                        onClick = { navController.navigate("booking") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp), // Hơi nhỏ lại một chút
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Brush.horizontalGradient(
                                    colors = listOf(AccentColor, Color(0xFFFFCC80))
                                ), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.FlashOn,
                                    contentDescription = "Đặt lịch nhanh",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "ĐẶT SÂN NGAY - CHƠI LIỀN TAY",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Clip
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // --- Tagline Thể thao ---
                    Text(
                        text = "Tìm kiếm sân tốt nhất, ngay lập tức!",
                        color = DarkTextColor.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}


/**
 * Chip cho các Danh mục/Lọc - Không đổi.
 */
@Composable
fun CategoryChip(category: String, isActive: Boolean, navController: NavController) {
    val backgroundColor = if (isActive) ActiveTagColor else Color.White
    val contentColor = if (isActive) Color.White else DarkTextColor

    Card(
        modifier = Modifier
            .clickable {
                navController.navigate("booking")
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = category,
            color = contentColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}


/**
 * Card Ngang Mới (Horizontal Layout) - Không đổi.
 */
@Composable
fun HorizontalCourtCard(imgId: Int, name: String, details: String, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { navController.navigate("court_detail/$name") },
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = name,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = name,
                    color = DarkTextColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Khoảng cách", tint = PrimaryColor, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = details.substringBefore(" - "), color = Color.Gray, fontSize = 12.sp)

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Đánh giá", tint = AccentColor, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = details.substringAfter(" - "), color = AccentColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryColor.copy(alpha = 0.1f))
                        .border(1.dp, PrimaryColor, RoundedCornerShape(8.dp))
                        .clickable { navController.navigate("booking") }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        "Đặt lịch ngay",
                        color = PrimaryColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}