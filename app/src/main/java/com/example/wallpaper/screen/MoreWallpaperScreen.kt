package com.example.wallpaper.screen

import Category
import Wall
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallpaper.Utils.LocalNavController
import com.example.wallpaper.ui.theme.ColorPrimary

@Composable
fun MoreWallpaperScreen(
    category: Category,
    onBackClick: () -> Unit
) {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = ColorPrimary
                )
            )
    ) {
        // Header
        TopAppBar(
            backgroundColor = Color.Transparent, // cho nền trong suốt
            elevation = 0.dp, // bỏ bóng
            modifier = Modifier
                .padding(top = 20.dp)
                .background(
                    brush = Brush.horizontalGradient(colors = ColorPrimary)
                ),
            title = { Text(category.categoryDes, fontSize = 20.sp, color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
            }
        )


        // Grid of Wallpapers
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(category.wallList.size) { index ->
                val wall = category.wallList[index]
                ImageCard(wall = wall, category = category, navController = navController)
            }
        }

        // Mock Ad Banner
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(80.dp)
//                .background(Color(0xFFF5F5F5))
//                .padding(8.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("Quảng cáo - Be App", fontSize = 14.sp, color = Color.Gray)
//        }
    }
}

@Composable
fun ImageCard(
    wall: Wall,
    category: Category,
    navController: NavController
) {
    val context = LocalContext.current
    val imageResId = remember(wall.wallThumb) {
        val nameWithoutExtension = wall.wallThumb.substringBefore(".")
        context.resources.getIdentifier(nameWithoutExtension, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .aspectRatio(9f / 16f)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.apply {
                        set("wall_data", wall)
                        set("category_data", category)
                    }

                navController.navigate("wallpaper_home_screen")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (imageResId != 0) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Fallback nếu không có ảnh
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("No Image", color = Color.White)
            }
        }
    }
}