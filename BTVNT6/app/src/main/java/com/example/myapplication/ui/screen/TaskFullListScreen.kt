package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.Task
import com.example.myapplication.ui.viewmodel.TaskListUiState
import com.example.myapplication.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFullListScreen(
    viewModel: TaskViewModel = viewModel(),
    onBackClick: () -> Unit,
    onTaskClick: (Int) -> Unit
) {
    val uiState by viewModel.taskListState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Danh sách công việc",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00BFA5))
            )
        },
        containerColor = Color(0xFFF8FAFB)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = uiState) {
                is TaskListUiState.Loading -> LoadingView()
                is TaskListUiState.Error -> ErrorView(state.message) { viewModel.loadTasks() }
                is TaskListUiState.Success -> {
                    if (state.tasks.isEmpty()) {
                        EmptyView()
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(state.tasks) { task ->
                                TaskListItem(
                                    task = task,
                                    onClick = { onTaskClick(task.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskListItem(task: Task, onClick: () -> Unit) {
    val statusColor = when (task.status?.lowercase()) {
        "pending" -> Color(0xFFFFF8E1)
        "in progress" -> Color(0xFFE1F5FE)
        "completed" -> Color(0xFFE8F5E9)
        else -> Color.White
    }

    val chipColor = when (task.status?.lowercase()) {
        "pending" -> Color(0xFFFFC107)
        "in progress" -> Color(0xFF2196F3)
        "completed" -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = statusColor),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = task.title ?: "Không có tiêu đề",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = task.description ?: "",
                fontSize = 14.sp,
                color = Color(0xFF555555)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = chipColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = task.status ?: "Unknown",
                        color = chipColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = task.date ?: "",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
