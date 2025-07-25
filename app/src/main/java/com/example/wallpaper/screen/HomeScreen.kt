package com.example.wallpaper.screen

import WallsPager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallpaper.Utils.LocalNavController
import com.example.wallpaper.model.WallpapersHomeViewModel
import com.example.wallpaper.ui.theme.ColorPrimary

@Composable
fun HomeScreen(viewModel: WallpapersHomeViewModel) {
    val navController = LocalNavController.current

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
                    .padding(top = 32.dp),
                Alignment.Center
            ) {
                Text(
                    "HÌNH NỀN ĐỘNG",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 28.sp,
                        FontWeight.Bold
                    ),
                )
            }
        }
        item { WallsPager(navController, viewModel) }
    }
}