package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.Task
import com.example.myapplication.ui.viewmodel.TaskListUiState
import com.example.myapplication.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = viewModel(),
    onTaskClick: (Int) -> Unit,
    onListClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val uiState by viewModel.taskListState.collectAsState()

    Scaffold(
        topBar = { UthHeaderModern() },
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = { viewModel.loadTasks() },
                onListClick = onListClick,
                onAddClick = onAddClick,
                onDetailClick = onDetailClick,
                onSettingsClick = onSettingsClick
            )
        },
        containerColor = Color(0xFFF7F9FC)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is TaskListUiState.Loading -> LoadingView()
                is TaskListUiState.Error -> ErrorView(state.message) { viewModel.loadTasks() }
                is TaskListUiState.Success -> {
                    if (state.tasks.isEmpty()) {
                        EmptyView()
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.tasks) { task ->
                                TaskCardModern(task = task) {
                                    onTaskClick(task.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UthHeaderModern() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF00BFA5))
            .statusBarsPadding()
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://nkclothing.sgp1.digitaloceanspaces.com/2025/07/18003827/logo-truong-dai-hoc-giao-thong-van-tai-tp-hcm-1.jpg",
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 6.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "UTH SmartTasks",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                text = "Quản lý công việc thông minh cho sinh viên",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun TaskCardModern(task: Task, onClick: () -> Unit) {
    val bgColor = when (task.status?.lowercase()) {
        "completed" -> Color(0xFFE8F5E9)
        "pending" -> Color(0xFFFFF8E1)
        "in progress" -> Color(0xFFE3F2FD)
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = task.title ?: "Untitled",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF1A1A1A)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = task.description ?: "",
                fontSize = 14.sp,
                color = Color(0xFF505050)
            )
            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusTagModern(task.status ?: "")
                Text(
                    text = task.date ?: "",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun StatusTagModern(status: String) {
    val color = when (status.lowercase()) {
        "in progress" -> Color(0xFF90CAF9)
        "pending" -> Color(0xFFFFE082)
        "completed" -> Color(0xFFA5D6A7)
        else -> Color(0xFFEEEEEE)
    }

    Surface(
        color = color,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = status.ifBlank { "Unknown" },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onListClick: () -> Unit,
    onAddClick: () -> Unit,
    onDetailClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF00BFA5),
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
            },
            label = { Text("Trang chủ", color = Color.White, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onListClick,
            icon = {
                Icon(Icons.Default.List, contentDescription = "List", tint = Color.White)
            },
            label = { Text("Danh sách", color = Color.White, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onAddClick,
            icon = {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color(0xFF00BFA5))
                }
            },
            label = { Text("Thêm", color = Color.White, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onDetailClick,
            icon = {
                Icon(Icons.Default.Description, contentDescription = "Detail", tint = Color.White)
            },
            label = { Text("Chi tiết", color = Color.White, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onSettingsClick,
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
            },
            label = { Text("Cài đặt", color = Color.White, fontSize = 12.sp) }
        )
    }
}

@Composable
fun EmptyView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🗒️", fontSize = 72.sp)
            Spacer(Modifier.height(12.dp))
            Text(
                "Chưa có công việc nào.\nHãy thêm một task mới nhé!",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color(0xFF00BFA5))
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message, color = Color.Red)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) { Text("Thử lại") }
        }
    }
}
