package com.example.btvn_uth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.* import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Import từ package chính
import com.example.btvn_uth.Destinations
import com.example.btvn_uth.R

// =========================================================================
// Phần 0: Top Bar chung và Data Model
// =========================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, onBackClick: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        title = { Text(title, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}

data class UIComponent(
    val name: String,
    val description: String,
    val route: String
)

val components = listOf(
    UIComponent("Text", "Displays text", Destinations.TEXT_DETAIL),
    UIComponent("Image", "Displays an image", Destinations.IMAGE_DETAIL),
    UIComponent("TextField", "Input field for text", Destinations.TEXTFIELD_DETAIL),
    UIComponent("PasswordField", "Input field for passwords", Destinations.TEXTFIELD_DETAIL),
    UIComponent("Column", "Arranges elements vertically", Destinations.LIST),
    UIComponent("Row", "Arranges elements horizontally", Destinations.ROW_LAYOUT_DETAIL),
    UIComponent("Tìm ra tất cả các thành phần UI cơ bản", "Tự tìm hiểu", Destinations.LIST)
)

// =========================================================================
// Phần 1: Màn hình Chào mừng (WelcomeScreen) - Slide 53
// =========================================================================
@Composable
fun WelcomeScreen(onReadyClick: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Icon (Cần có R.drawable.ic_jetpack_compose_logo)
            Image(
                painter = painterResource(id = R.drawable.ic_jetpack_compose_logo),
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier.size(120.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Jetpack Compose", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }

            Button(
                onClick = onReadyClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("I'm ready", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

// =========================================================================
// Phần 2: Màn hình Danh sách (ComponentsListScreen) - Slide 55
// =========================================================================
@Composable
fun ComponentsListScreen(
    onNavigateToDetail: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar("UI Components List", onBackClick) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item { ListHeader("Display") }
            items(components.take(2)) { component ->
                ComponentItem(component) { onNavigateToDetail(component.route) }
            }
            item { ListHeader("Input") }
            items(components.slice(2..3)) { component ->
                ComponentItem(component) { onNavigateToDetail(component.route) }
            }
            item { ListHeader("Layout") }
            items(components.slice(4..5)) { component ->
                ComponentItem(component) { onNavigateToDetail(component.route) }
            }
            item { ListHeader("Tự tìm hiểu") }
            item {
                ComponentItem(components.last()) { /* Không điều hướng */ }
            }
        }
    }
}

@Composable
fun ComponentItem(component: UIComponent, onClick: () -> Unit) {
    val isSpecial = component.name.contains("Tự tìm hiểu")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(if (isSpecial) Color(0xFFFFCDD2) else Color(0xFFE3F2FD))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(component.name, style = MaterialTheme.typography.titleMedium, color = Color.Black)
        Text(component.description, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
    }
    Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.White, thickness = 1.dp)
}

@Composable
fun ListHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = Color(0xFF1565C0),
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 4.dp)
    )
}

// =========================================================================
// Phần 3: Màn hình Văn bản (Text Detail Screen) - Slide 56
// =========================================================================
@Composable
fun TextDetailScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar("Text Detail", onBackClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("The quick ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF795548))) {
                        append("Brown")
                    }
                    append("\nfox jumps ")
                    withStyle(style = SpanStyle(fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.SemiBold)) {
                        append("over")
                    }
                    append("\nthe ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline, fontStyle = FontStyle.Italic)) {
                        append("lazy dog.")
                    }
                },
                fontSize = 20.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}

// =========================================================================
// Phần 4: Màn hình Hình ảnh (Image Detail Screen) - Slide 56
// =========================================================================
@Composable
fun ImageDetailScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar("Images", onBackClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ảnh 1 (Cần có R.drawable.building_image)
            Image(
                painter = painterResource(id = R.drawable.building_image),
                contentDescription = "Building Image 1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(Modifier.height(8.dp))

            // Text Link
            Text(
                text = "https://uet.vnu.edu.vn/wp-content/uploads/2015/01/LogouetVNU.png",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Blue
            )
            Spacer(Modifier.height(16.dp))

            // Ảnh 2 (Cần có R.drawable.lower_building_image)
            Image(
                painter = painterResource(id = R.drawable.lower_building_image),
                contentDescription = "Building Image 2",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(Modifier.height(8.dp))
            Text(text = "In app", style = MaterialTheme.typography.bodySmall)
        }
    }
}

// =========================================================================
// Phần 5: Màn hình TextField (TextField Detail Screen) - Slide 56
// =========================================================================
@Composable
fun TextFieldDetailScreen(onBackClick: () -> Unit) {
    var textInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar("TextField", onBackClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TextField thông thường
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Thông tin nhập") },
                modifier = Modifier.fillMaxWidth()
            )

            // PasswordField
            Spacer(Modifier.height(16.dp))
            var passwordInput by remember { mutableStateOf("") }
            OutlinedTextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Tự động cập nhật theo $textInput",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
        }
    }
}

// =========================================================================
// Phần 6: Màn hình Row Layout (Row Layout Detail Screen) - Slide 56
// =========================================================================
@Composable
fun RowLayoutDetailScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar("Row Layout", onBackClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tạo 4 hàng, mỗi hàng 3 ô vuông
            repeat(4) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { columnIndex ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp)
                                .padding(horizontal = 8.dp)
                                .background(
                                    // Màu sắc xen kẽ
                                    color = if (columnIndex % 2 == 0) Color(0xFF90CAF9) else Color(0xFF42A5F5),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
    }
}