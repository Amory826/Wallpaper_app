package com.example.wallpaper.model

data class Wall(
    val wallId: String = "",
    val type: Int = 0,
    val wallThumb: String = "",
    val wallHashtag: String = "",
    val dataSet: String = "",
    val isPremium: Boolean = false
)
