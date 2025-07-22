// TrendingScreen.kt
package com.example.wallpaper.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpaper.Utils.ImageCard
import com.example.wallpaper.model.Category
import com.example.wallpaper.ui.theme.ColorPrimary

@Composable
fun TrendingScreen(categories: List<Category>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = ColorPrimary
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(bottom = 56.dp) // chừa chỗ cho BottomBar
    ) {
        Text(
            text = "BỘ SƯU TẬP",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 30.dp)
        )

        categories.forEach { category ->
            Text(
                text = category.categoryDes,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, top = 12.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(category.wallList.size) { item ->
                    ImageCard(wallThumb = category.wallList[item].wallThumb)
                }

                item {
                    Box(
                        modifier = Modifier
                            .size(width = 80.dp, height = 160.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF1F1F1))
                            .clickable { /* TODO: Xử lý khi nhấn "Xem thêm" */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = "Xem thêm",
                                tint = Color.Black
                            )
                            Text("Xem thêm", fontSize = 12.sp, color = Color.Black)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(50.dp))
    }
}