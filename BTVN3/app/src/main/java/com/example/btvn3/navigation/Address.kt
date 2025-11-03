package com.example.btvn3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvn3.screens.Animation
import com.example.btvn3.screens.BTVN1
import com.example.btvn3.screens.Dialog
import com.example.btvn3.screens.Display
import com.example.btvn3.screens.Home
import com.example.btvn3.screens.Input
import com.example.btvn3.screens.Layout
import com.example.btvn3.screens.List_
import com.example.btvn3.screens.Navigation


@Composable
fun Address(navController: NavController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "btvn1" // Màn hình bắt đầu khi ứng dụng chạy
    ) {
        composable("btvn1") { BTVN1(navController = navController) }
        composable("home") { Home(navController = navController) }
        composable("display") { Display(navController = navController) }
        composable("input") { Input(navController = navController) }
        composable("layout") { Layout(navController = navController) }
        composable("navigation") { Navigation(navController = navController) }
        composable("list") { List_(navController = navController) }
        composable("animation") { Animation(navController = navController) }
        composable("dialog") { Dialog(navController = navController) }
        composable("detail") { Detail(navController = navController) }
    }
}

@Composable
fun Detail(navController: NavController) {
    // Thêm nội dung cho Detail ở đây
}
