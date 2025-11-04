package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.TaskListScreen
import com.example.myapplication.ui.screen.TaskFullListScreen
import com.example.myapplication.ui.screen.TaskDetailScreen

// Khai báo các route (mỗi màn hình một route riêng)
sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")              // Trang chính
    object TaskFullList : Screen("task_full_list")    // Trang danh sách đầy đủ
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: Int) = "task_detail/$taskId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.TaskList.route
    ) {

        // Trang chủ - hiển thị danh sách rút gọn
        composable(Screen.TaskList.route) {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate(Screen.TaskDetail.createRoute(taskId))
                },
                onListClick = {
                    navController.navigate(Screen.TaskFullList.route)
                },
                onAddClick = {
                    // Tùy chọn nếu bạn muốn tạo màn thêm task
                },
                onDetailClick = {
                    // Mở thử chi tiết task ID=1
                    navController.navigate(Screen.TaskDetail.createRoute(1))
                },
                onSettingsClick = {
                    // Sau này có thể mở trang cài đặt
                }
            )
        }

        // Trang danh sách đầy đủ
        composable(Screen.TaskFullList.route) {
            TaskFullListScreen(
                onBackClick = { navController.popBackStack() },
                onTaskClick = { taskId ->
                    navController.navigate(Screen.TaskDetail.createRoute(taskId))
                }
            )
        }

        // Trang chi tiết
        composable(
            route = Screen.TaskDetail.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(
                taskId = taskId,
                navController = navController
            )
        }
    }
}
