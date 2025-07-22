package com.example.wallpaper.screen

import Category
import Wall
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wallpaper.Utils.WallpaperDetailItem


@Composable
fun WallpaperHomeScreen(
    wall: Wall,
    category: Category,
    onFavoriteClick: (Wall) -> Unit,
    onShareClick: (Wall) -> Unit,
    onDownloadClick: (Wall) -> Unit
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { category.wallList.size }
    )

    VerticalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 50.dp),
        contentPadding = PaddingValues(0.dp),
        pageSpacing = 0.dp
    ) { page ->
        val wall = category.wallList[page]
        WallpaperDetailItem(
            wall = wall,
            onFavoriteClick = { onFavoriteClick(wall) },
            onShareClick = { onShareClick(wall) },
            onDownloadClick = { onDownloadClick(wall) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

