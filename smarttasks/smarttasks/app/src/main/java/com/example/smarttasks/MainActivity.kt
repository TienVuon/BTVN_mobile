package com.example.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import com.example.smarttasks.ui.theme.SmarttasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmarttasksTheme {
                // NavController và trạng thái đăng nhập
                val navController = rememberNavController()
                var isLoggedIn by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser != null) }

                // Scaffold với SnackbarHost để hiển thị thông báo
                Scaffold(snackbarHost = { SnackbarHost(SnackbarHostState()) }) { padding ->
                    // NavHost để điều hướng giữa các màn hình
                    NavHost(navController = navController, startDestination = if (isLoggedIn) "profile" else "login") {
                        composable("login") {
                            LoginScreen(navController, onLoginSuccess = {
                                isLoggedIn = true
                            })
                        }
                        composable("profile") {
                            ProfileScreen {
                                // Khi đăng xuất, cập nhật trạng thái và quay lại màn hình đăng nhập
                                isLoggedIn = false
                                navController.popBackStack("login", inclusive = false)
                            }
                        }
                    }
                }
            }
        }
    }
}
