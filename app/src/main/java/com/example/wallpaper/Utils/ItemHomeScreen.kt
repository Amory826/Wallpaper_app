package com.example.wallpaper.Utils

import Wall
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallpaper.R
import com.example.wallpaper.model.WallpapersHomeViewModel

@Composable
fun WallPagerItem(wall: Wall, navController: NavController?, viewModel: WallpapersHomeViewModel) {
    // Lấy thông tin cấu hình màn hình
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    // Giới hạn chiều cao ở 75% chiều cao màn hình
    val itemHeight =
        (screenHeight * 0.75f).coerceAtMost(screenHeight * 0.8f) // Đảm bảo không vượt 75%

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(itemHeight)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = getWallDrawable(wall.wallThumb)),
            contentDescription = wall.dataSet,
            modifier = Modifier
                .clickable {
                    val category = viewModel.getCategoryByWall(wall.type)
                    if (category != null) {
                        navController?.currentBackStackEntry?.savedStateHandle?.apply {
                            set("wall_data", wall)
                            set("category_data", category)
                        }
                        navController?.navigate("wallpaper_home_screen")
                    }

                }
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    }
}

fun getWallDrawable(fileName: String): Int {
    return when (fileName) {
        "bo_xuong.jpg" -> R.drawable.bo_xuong
        "co_nguoi.jpg" -> R.drawable.co_nguoi
        "cau_vang.jpg" -> R.drawable.cua_vang
        "cuoi_nao.jpg" -> R.drawable.cuoi_nao
        "doremon.jpg" -> R.drawable.doremon
        "man_dem.jpg" -> R.drawable.man_dem
        "mo_di.jpg" -> R.drawable.mo_di
        "thuyen.jpg" -> R.drawable.thuyen
        else -> R.drawable.bo_xuong // Ảnh mặc định fallback
    }
}