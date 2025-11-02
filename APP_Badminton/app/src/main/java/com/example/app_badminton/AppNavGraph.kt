package com.example.app_badminton

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_badminton.navigation.BottomNavigationBar

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // 🔹 Chỉ hiển thị thanh công cụ nếu không phải Login hoặc Register
            if (currentRoute !in listOf("login", "register")) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {

            // ❌ Không có thanh công cụ
            composable("login") { LoginScreen(navController = navController) }
            composable("register") { RegisterScreen(navController = navController) }

            // ✅ Có thanh công cụ
            composable("home") { HomeScreen(navController = navController) }
            composable("cart") { CartScreen(navController = navController) }
            composable("profile") { ProfileScreen(navController = navController) }
            composable("booking") { BookingScreen(navController = navController) }

            // ✅ Chuyển động theo sân cụ thể — truyền courtName
            composable(
                route = "court_booking_detail/{courtName}",
                arguments = listOf(navArgument("courtName") { type = NavType.StringType })
            ) { backStackEntry ->
                val courtName = backStackEntry.arguments?.getString("courtName") ?: ""
                CourtBookingDetailScreen(navController = navController, courtName = courtName)
            }

            // ✅ Trang thanh toán
            composable("payment") {
                PaymentScreen(navController = navController)
            }
        }
    }
}
