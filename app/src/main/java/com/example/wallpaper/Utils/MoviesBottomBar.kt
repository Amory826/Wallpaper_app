package com.example.wallpaper.Utils

import androidx.compose.foundation.background
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.wallpaper.model.MovieNavType
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wallpaper.ui.theme.ColorPrimary

@Composable
fun MoviesBottomBar(navType: MutableState<MovieNavType>) {
    NavigationBar(
        containerColor = ColorPrimary,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
            selected = navType.value == MovieNavType.SHOWING,
            onClick = { navType.value = MovieNavType.SHOWING },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Outlined.MovieCreation, contentDescription = null) },
            selected = navType.value == MovieNavType.TRENDING,
            onClick = { navType.value = MovieNavType.TRENDING },
            label = { Text("Trending") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Outlined.LibraryAdd, contentDescription = null) },
            selected = navType.value == MovieNavType.WATCHLIST,
            onClick = { navType.value = MovieNavType.WATCHLIST },
            label = { Text("Watchlist") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            selected = navType.value == MovieNavType.PROFILE,
            onClick = { navType.value = MovieNavType.PROFILE },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )
    }
}
