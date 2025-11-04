package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.viewmodel.TaskViewModel
import com.example.myapplication.ui.viewmodel.TaskDetailUiState
import com.example.myapplication.data.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    val uiState by viewModel.taskDetailState.collectAsState()

    // tải dữ liệu mỗi khi mở màn
    LaunchedEffect(taskId) {
        viewModel.loadTaskDetail(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DETAIL", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00BFA5))
            )
        },
        floatingActionButton = {
            if (uiState is TaskDetailUiState.Success) {
                val task = (uiState as TaskDetailUiState.Success).task
                FloatingActionButton(
                    onClick = {
                        viewModel.deleteTask(task.id) {
                            navController.popBackStack() // quay lại sau khi xóa
                        }
                    },
                    containerColor = Color(0xFFD32F2F)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                }
            }
        },
        containerColor = Color(0xFFF8FAFB)
    ) { padding ->
        when (val state = uiState) {
            is TaskDetailUiState.Loading -> LoadingView()
            is TaskDetailUiState.Error -> ErrorView(state.message) { viewModel.loadTaskDetail(taskId) }
            is TaskDetailUiState.Success -> {
                TaskDetailContent(task = state.task, modifier = Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun TaskDetailContent(task: Task, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = task.title ?: "Không có tiêu đề",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color(0xFF1A1A1A)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = task.description ?: "Không có mô tả",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color(0xFF555555)
            )
        }

        item {
            Surface(
                color = Color(0xFFE0F2F1),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Thể loại: ${task.category ?: "Không rõ"}", color = Color(0xFF00796B))
                    Text("Trạng thái: ${task.status ?: "Không rõ"}", color = Color(0xFF00796B))
                    Text("Độ ưu tiên: ${task.priority ?: "Không rõ"}", color = Color(0xFF00796B))
                    Text("Ngày: ${task.date ?: "Không rõ"}", color = Color(0xFF00796B))
                }
            }
        }

        if (!task.subtasks.isNullOrEmpty()) {
            item {
                Text("Công việc con:", fontWeight = FontWeight.SemiBold)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    task.subtasks.forEach {
                        Surface(
                            color = Color(0xFFF1F8E9),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "• $it",
                                modifier = Modifier.padding(10.dp),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }
                    }
                }
            }
        }

        if (!task.attachments.isNullOrEmpty()) {
            item {
                Text("Tệp đính kèm:", fontWeight = FontWeight.SemiBold)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    task.attachments.forEach {
                        Surface(
                            color = Color(0xFFE3F2FD),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "📎 $it",
                                modifier = Modifier.padding(10.dp),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Chi tiết công việc hiển thị đầy đủ dữ liệu từ API.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}
