package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal class {
    fun CardScreen(): @Composable `fun`? {
        Column(
            fillMaxSize()
                .padding(24.0, TODO("Cannot convert element"), p).also { modifier = it }
        )
        run {
            Icon(
                Default.ArrowBack.also { imageVector = it },
                "Back".also { contentDescription = it },
                Color.Companion.Black.also { tint = it },
                size(28.0, TODO("Cannot convert element"), p)
                    .clickable.also { modifier = it })
            run { navController.popBackStack() }
            Spacer(height(40.0, TODO("Cannot convert element"), p).also { modifier = it })

            Card(
                fillMaxWidth()
                    .padding(8.0, TODO("Cannot convert element"), p).also { modifier = it },
                cardElevation(8.0, TODO("Cannot convert element"), p).also { elevation = it },
                cardColors(Color(-0x70606).also { containerColor = it }).also { colors = it }
            )
            run {
                Column(padding(16.0, TODO("Cannot convert element"), p).also { modifier = it })
                run {
                    Text(
                        "Jetpack Compose Card".also { text = it },
                        typography.titleLarge.also { style = it }
                    )
                    Spacer(height(8.0, TODO("Cannot convert element"), p).also { modifier = it })
                    Text(
                        "Card dùng để hiển thị nội dung trong khung bo tròn có bóng, rất phổ biến trong giao diện hiện đại.".also {
                            text = it
                        },
                        typography.bodyMedium.also { style = it }
                    )
                }
            }
        }
    }
}