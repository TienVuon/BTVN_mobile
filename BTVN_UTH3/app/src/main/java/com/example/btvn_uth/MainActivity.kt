package com.example.btvn_uth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Đảm bảo import các Composable từ file BTVN_UTH.kt
import com.example.btvn_uth.ui.components.WelcomeScreen
import com.example.btvn_uth.ui.components.ComponentsListScreen
import com.example.btvn_uth.ui.components.TextDetailScreen
import com.example.btvn_uth.ui.components.ImageDetailScreen
import com.example.btvn_uth.ui.components.TextFieldDetailScreen
import com.example.btvn_uth.ui.components.RowLayoutDetailScreen
// Thay thế bằng theme name thực tế của bạn
import com.example.btvn_uth.ui.theme.BTVN_UTHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_UTHTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

// Định nghĩa các Route (Đích đến) cho Navigation
object Destinations {
    const val WELCOME = "welcome"
    const val LIST = "list"
    const val TEXT_DETAIL = "text_detail"
    const val IMAGE_DETAIL = "image_detail"
    const val TEXTFIELD_DETAIL = "textfield_detail"
    const val ROW_LAYOUT_DETAIL = "row_layout_detail"
}

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.WELCOME
    ) {
        composable(Destinations.WELCOME) {
            WelcomeScreen(onReadyClick = { navController.navigate(Destinations.LIST) })
        }
        composable(Destinations.LIST) {
            ComponentsListScreen(
                onNavigateToDetail = { route -> navController.navigate(route) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Destinations.TEXT_DETAIL) { TextDetailScreen(onBackClick = { navController.popBackStack() }) }
        composable(Destinations.IMAGE_DETAIL) { ImageDetailScreen(onBackClick = { navController.popBackStack() }) }
        composable(Destinations.TEXTFIELD_DETAIL) { TextFieldDetailScreen(onBackClick = { navController.popBackStack() }) }
        composable(Destinations.ROW_LAYOUT_DETAIL) { RowLayoutDetailScreen(onBackClick = { navController.popBackStack() }) }
    }
}