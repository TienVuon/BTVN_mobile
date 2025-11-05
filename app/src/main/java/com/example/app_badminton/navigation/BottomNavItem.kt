package com.example.app_badminton.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Danh sách các nút trong thanh điều hướng dưới (Bottom Navigation)
 */
sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        label = "Trang chủ",
        icon = Icons.Outlined.Home
    )

    object Booking : BottomNavItem(
        route = "booking",
        label = "Đặt lịch",
        icon = Icons.Outlined.CalendarMonth
    )

    object Cart : BottomNavItem(
        route = "cart",
        label = "Giỏ hàng",
        icon = Icons.Outlined.ShoppingCart
    )

    object Profile : BottomNavItem(
        route = "profile",
        label = "Tài khoản",
        icon = Icons.Outlined.Person
    )
}
