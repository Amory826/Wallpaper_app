package com.example.wallpaper.Utils

import Wall
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WallpaperDetailItem(
    wall: Wall,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val imageResId = remember(wall.wallThumb) {
        val nameWithoutExtension = wall.wallThumb.substringBefore(".")
        context.resources.getIdentifier(nameWithoutExtension, "drawable", context.packageName)
    }

    var checkShow by remember { mutableStateOf(true) }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            checkShow = !checkShow
        }) {
        // Ảnh nền
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Các nút thao tác bên phải
        if (checkShow) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Yêu thích",
                        tint = Color.White
                    )
                }

                IconButton(onClick = onShareClick) {
                    Icon(Icons.Default.Share, contentDescription = "Chia sẻ", tint = Color.White)
                }

                IconButton(onClick = onDownloadClick) {
                    Icon(
                        Icons.Default.Download,
                        contentDescription = "Tải xuống",
                        tint = Color.White
                    )
                }
            }

            // Hashtag + quảng cáo ở dưới cùng
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .padding(16.dp)
            ) {
                Text(
                    text = wall.wallHashtag,
                    color = Color.White,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                AdBanner()
            }
        }

    }
}

@Composable
fun AdBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF2D2D2D)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ad: MahjongRush - Cài đặt ngay",
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun WallpaperDetailScreenPreview() {
    val wall = Wall(
        wallId = "abc123",
        wallThumb = "https://example.com/image.jpg",
        wallHashtag = "#animegirl, #anime, #blue",
        isPremium = false
    )

    WallpaperDetailItem(
        wall = wall,
        onFavoriteClick = { /* xử lý yêu thích */ },
        onShareClick = { /* xử lý chia sẻ */ },
        onDownloadClick = { /* xử lý tải xuống */ },
        modifier = Modifier
    )
}
