package com.example.wallpaper.model

data class Category(
    val categoryId: String = "",
    val categoryDes: String = "",
    val categoryThumb: String = "",
    val wallList: List<Wall> = emptyList()
)