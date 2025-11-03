package com.example.tuan3.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.tuan3.screens.BTVN1
import com.example.tuan3.screens.Home
import com.example.tuan3.screens.Display
import com.example.tuan3.screens.Input
import com.example.tuan3.screens.Layout
import com.example.tuan3.screens.Navigation
import com.example.tuan3.screens.List_
import com.example.tuan3.screens.Animation
import com.example.tuan3.screens.Dialog


@Composable
fun Address(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "btvn1"
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
    }
}