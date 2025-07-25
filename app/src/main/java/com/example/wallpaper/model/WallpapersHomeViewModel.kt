package com.example.wallpaper.model

import Category
import Wall
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Factory tạo ViewModel
@Suppress("UNCHECKED_CAST")
class WallpapersHomeViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WallpapersHomeViewModel(context) as T
    }
}

// ViewModel chính
class WallpapersHomeViewModel(context: Context) : ViewModel() {

    // Fake repository dữ liệu mẫu
    private val wallRepository = listOf(
        Wall("0", "3", "bo_xuong.jpg", "#nature", "Nature", false),
        Wall("1", "0", "co_nguoi.jpg", "#nature", "Nature", false),
        Wall("2", "1", "cau_vang.jpg", "#city", "Urban", true),
        Wall("3", "2", "cuoi_nao.jpg", "#city", "Urban", true),
        Wall("4", "2", "doremon.jpg", "#city", "Urban", true),
        Wall("5", "1", "man_dem.jpg", "#city", "Urban", true),
        Wall("6", "0", "mo_di.jpg", "#city", "Urban", true),
        Wall("7", "1", "thuyen.jpg", "#city", "Urban", true),
        Wall("8", "0", "bo_xuong.jpg", "#city", "Urban", true),
        Wall("9", "2", "cuoi_nao.jpg", "#city", "Urban", true),
        Wall("10", "3", "co_nguoi.jpg", "#city", "Urban", true),
    )

    val categories = listOf(
        Category(
            categoryId = "0",
            categoryDes = "Nature",
            categoryThumb = "bo_xuong.jpg",
            wallList = listOf(
                Wall("0", "0", "bo_xuong.jpg", "#nature", "Nature", false),
                Wall("1", "0", "co_nguoi.jpg", "#nature", "Nature", false)
            )
        ),
        Category(
            categoryId = "1",
            categoryDes = "Abstract",
            categoryThumb = "bo_xuong.jpg",
            wallList = listOf(
                Wall("0", "1", "bo_xuong.jpg", "#nature", "Nature", false),
                Wall("1", "1", "co_nguoi.jpg", "#nature", "Nature", false),
                Wall("8", "1", "bo_xuong.jpg", "#city", "Urban", true),
                Wall("9", "1", "cuoi_nao.jpg", "#city", "Urban", true),
                Wall("10", "1", "co_nguoi.jpg", "#city", "Urban", true)
            )
        ),
        Category(
            categoryId = "2",
            categoryDes = "Space",
            categoryThumb = "bo_xuong.jpg",
            wallList = listOf(
                Wall("0", "2", "bo_xuong.jpg", "#nature", "Nature", false),
                Wall("1", "2", "co_nguoi.jpg", "#nature", "Nature", false),
                Wall("5", "2", "man_dem.jpg", "#city", "Urban", true),
                Wall("6", "2", "mo_di.jpg", "#city", "Urban", true),
                Wall("7", "2", "thuyen.jpg", "#city", "Urban", true),
            )
        ),
        Category(
            categoryId = "3",
            categoryDes = "Urban",
            categoryThumb = "cau_vang.jpg",
            wallList = listOf(
                Wall("3", "3", "cuoi_nao.jpg", "#city", "Urban", true),
                Wall("4", "3", "doremon.jpg", "#city", "Urban", true),
                Wall("5", "3", "man_dem.jpg", "#city", "Urban", true),
                Wall("6", "3", "mo_di.jpg", "#city", "Urban", true),
                Wall("7", "3", "thuyen.jpg", "#city", "Urban", true),
                Wall("8", "3", "bo_xuong.jpg", "#city", "Urban", true),
                Wall("9", "3", "cuoi_nao.jpg", "#city", "Urban", true),
                Wall("10", "3", "co_nguoi.jpg", "#city", "Urban", true)
            )
        )
    )


    // Dữ liệu được expose ra UI
    private val _wallList = MutableStateFlow<List<Wall>>(emptyList())
    val wallList: StateFlow<List<Wall>> get() = _wallList

    fun getCategoryByWall(wallId: String): Category? {
        return categories.find { it.categoryId == wallId }
    }

    fun getWallsByCategoryId(categoryId: String): List<Wall> {
        return categories.find { it.categoryId == categoryId }?.wallList ?: emptyList()
    }

    init {
        // Load dữ liệu khi khởi tạo ViewModel
        _wallList.value = wallRepository
    }
}
