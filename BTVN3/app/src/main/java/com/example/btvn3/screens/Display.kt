
package com.example.btvn3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.btvn3.R

@Composable
fun Display(navController: NavController) {
    var flag by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // CĂN GIỮA NGANG
    ) {
        // * * * ================= LAYOUT ================= * * *
        Text(
            "LAYOUT",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            modifier = Modifier.clickable {
                navController.navigate("home")
            }
        )
        if (flag == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // * * * ================= TEXT ================= * * *
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 1 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Text",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // * * * ================= Image ================= * * *
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 2 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Image",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // * * * ================= ICON ================= * * *
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 3 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Icon",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // * * * ================= Button ================= * * *
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { flag = 4 },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB0E3F4),
                        contentColor = Color.Black
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Button",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        } else if (flag == 1) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Text",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "Kích thước",
                    fontSize = 24.sp
                )
                Text(
                    text = "Kích thức 16sp *fontSize = 16.sp*",
                    fontSize = 16.sp
                )
                Text(
                    text = "Kích thức 21sp *fontSize = 14.sp*\n\n",
                    fontSize = 14.sp
                )


                Text(
                    text = "In",
                    fontSize = 24.sp
                )
                Text(
                    text = "In đậm *fontWeight = FontWeight.Bold*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "In rất đậm *fontWeight = FontWeight.ExtraBold*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "In mỏng *fontWeight = FontWeight.Light*\n\n",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = "Nghiêng",
                    fontSize = 24.sp,
                )
                Text(
                    text = "Chữ nghiêng *fontStyle = FontStyle.Italic*\n\n",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = "Gạch chân/ngang",
                    fontSize = 24.sp,
                )
                Text(
                    text = "gạch chân *textDecoration = TextDecoration.Underline*",
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                )
                Text(
                    text = "Chữ gạch ngang *textDecoration = TextDecoration.LineThrough*",
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = "Vừa gạch chân vừa gạch ngang *\n" +
                            "textDecoration = TextDecoration.combine(" +
                            "listOf(TextDecoration.Underline, TextDecoration.LineThrough)\n\n",
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.combine(
                        listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                    )
                )

                Text(
                    text = "Màu chữ",
                    fontSize = 24.sp,
                )
                Text(
                    text = "color trực tiếp *color = Color.Red*",
                    fontSize = 14.sp,
                    color = Color.Red
                )
                Text(
                    text = "color trực tiếp *color = Color(0xFF4CAF50)*",
                    fontSize = 14.sp,
                    color = Color(0xFF4CAF50)
                )
                Text(
                    text = "MaterialTheme color *color = MaterialTheme.colorScheme.primary*",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "MaterialTheme color * color = MaterialTheme.colorScheme.secondary*",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        } else if (flag == 2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Image",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "Ảnh trong drawable *painter = painterResource(id = R.drawable.my_image)*",
                    fontSize = 14.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.jetpack_compose),
                    contentDescription = "Mô tả hình ảnh"
                )
                Text(text = "\n\n")

//                Text(
//                    text = "Ảnh trên internet *painter = rememberAsyncImagePainter(<link>)*",
//                    fontSize = 14.sp
//                )
//                Image(
//                    painter = rememberAsyncImagePainter(
//                        model = "https://picsum.photos/300/400"
//                    ),
//                    contentDescription = "Reze image"
//                )
            }

        } else if (flag == 3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Icon",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )


                }
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "Kích thước Icon",
                    fontSize = 24.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Icon 16dp",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Kích thước 16dp *modifier = Modifier.size(16.dp)*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Icon 24dp",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Kích thước 24dp *modifier = Modifier.size(24.dp)*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Icon 32dp",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Kích thước 32dp *modifier = Modifier.size(32.dp)*\n\n",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Màu sắc Icon",
                    fontSize = 24.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Icon màu đỏ",
                        tint = Color.Red
                    )
                    Text(
                        text = "Màu đỏ *tint = Color.Red*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Icon màu xanh",
                        tint = Color(0xFF2196F3)
                    )
                    Text(
                        text = "Màu xanh *tint = Color(0xFF2196F3)*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Icon primary",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Material primary *tint = MaterialTheme.colorScheme.primary*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Icon secondary",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Material secondary *tint = MaterialTheme.colorScheme.secondary*\n\n",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Các loại Icon",
                    fontSize = 24.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icon filled"
                    )
                    Text(
                        text = "Filled Icon *Icons.Default.Star*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Icon outlined"
                    )
                    Text(
                        text = "Outlined Icon *Icons.Outlined.Star*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Icon rounded"
                    )
                    Text(
                        text = "Rounded Icon *Icons.Rounded.Star*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Sharp.Star,
                        contentDescription = "Icon sharp"
                    )
                    Text(
                        text = "Sharp Icon *Icons.Sharp.Star*\n\n",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Icon với background",
                    fontSize = 24.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Blue, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Icon với background",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Icon với background *Box + background*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Icon với padding",
                        modifier = Modifier
                            .background(Color.Gray, CircleShape)
                            .padding(8.dp)
                            .size(24.dp),
                        tint = Color.White
                    )
                    Text(
                        text = "Icon với padding *modifier.padding()*\n\n",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Icon Button",
                    fontSize = 24.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { /* Xử l click */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Icon button"
                        )
                    }
                    Text(
                        text = "IconButton *có thể click*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledIconButton(
                        onClick = { /* Xử l click */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Filled icon button"
                        )
                    }
                    Text(
                        text = "FilledIconButton *có nền*",
                        fontSize = 14.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedIconButton(
                        onClick = { /* Xử l click */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Outlined icon button"
                        )
                    }
                    Text(
                        text = "OutlinedIconButton *có viền*\n\n",
                        fontSize = 14.sp
                    )
                }
            }
        } else if (flag == 4) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { flag = 0 },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB0E3F4),
                    contentColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Button",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 34.sp
                    )
                }
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "Các loại Button",
                    fontSize = 24.sp
                )

                Text(
                    text = "Filled Button",
                    fontSize = 18.sp
                )
                Button(
                    onClick = { }
                ) {
                    Text("Filled Button *Button()*")
                }

                Text(
                    text = "Filled Tonal Button",
                    fontSize = 18.sp
                )
                FilledTonalButton(
                    onClick = { }
                ) {
                    Text("Filled Tonal Button *FilledTonalButton()*")
                }

                Text(
                    text = "Outlined Button",
                    fontSize = 18.sp
                )
                OutlinedButton(
                    onClick = { }
                ) {
                    Text("Outlined Button *OutlinedButton()*")
                }

                Text(
                    text = "Text Button",
                    fontSize = 18.sp
                )
                TextButton(
                    onClick = { }
                ) {
                    Text("Text Button *TextButton()*")
                }

                Text(
                    text = "Elevated Button",
                    fontSize = 18.sp
                )
                ElevatedButton(
                    onClick = { }
                ) {
                    Text("Elevated Button *ElevatedButton()*")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Button với Icon",
                    fontSize = 24.sp
                )
                Button(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Button với Icon")
                }

                FilledTonalButton(
                    onClick = { }
                ) {
                    Text("Start Icon")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Kích thước Button",
                    fontSize = 24.sp
                )
                Button(
                    onClick = { },
                    modifier = Modifier.width(200.dp)
                ) {
                    Text("Width 200dp")
                }

                Button(
                    onClick = { },
                    enabled = false
                ) {
                    Text("Disabled Button *enabled = false*")
                }
            }
        }
    }
}
