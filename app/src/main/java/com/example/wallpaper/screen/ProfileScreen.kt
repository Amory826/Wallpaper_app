package com.example.wallpaper.screen

import IconData
import ItemProfile
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpaper.ui.theme.ColorPrimary

@Composable
fun ProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = ColorPrimary
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .padding(top = 40.dp)
            ) {
                Text(
                    "Cá nhân", style = TextStyle(
                        color = Color.Black,
                        fontSize = 30.sp,
                        FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ItemProfile(
                iconData = IconData(Icons.Filled.Language, "Language Icon"),
                title = "Ngôn ngữ",
                onClick = {
                    // Thực hiện hành động khi bấm
                    println("Item clicked")
                }
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            ItemProfile(
                iconData = IconData(Icons.Filled.StarOutline, "Đánh giá"),
                title = "Đánh giá",
                onClick = {
                    // Thực hiện hành động khi bấm
                    println("Item clicked")
                }
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            ItemProfile(
                iconData = IconData(Icons.Filled.Send, ""),
                title = "Liên hệ với chúng tôi",
                onClick = {
                    // Thực hiện hành động khi bấm
                    println("Item clicked")
                })
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            ItemProfile(
                iconData = IconData(Icons.Filled.Lock, ""),
                title = "Chính sách bảo mật",
                onClick = {
                    // Thực hiện hành động khi bấm
                    Log.d("TagAmory", "Item clicked")
                })

        }
    }
}

// Ví dụ sử dụng
@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}