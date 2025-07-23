package com.example.wallpaper

import Category
import Wall
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpaper.ui.theme.WallpaperTheme
import com.example.wallpaper.Utils.MoviesBottomBar
import com.example.wallpaper.model.MovieNavType
import com.example.wallpaper.model.WallpapersHomeViewModel
import com.example.wallpaper.model.WallpapersHomeViewModelFactory
import com.example.wallpaper.screen.HomeScreen
import com.example.wallpaper.screen.MoreWallpaperScreen
import com.example.wallpaper.screen.ProfileScreen
import com.example.wallpaper.screen.TrendingScreen
import com.example.wallpaper.screen.WallpaperHomeScreen
import com.example.wallpaper.screen.WatchListScreen
import com.example.wallpaper.ui.theme.ColorPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallpaperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val sharedPrefs =
                        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

                    val startDestination = if (
                        sharedPrefs.contains("age_user") &&
                        sharedPrefs.getString("age_user", null) != null
                    ) {
                        "baseScreen"
                    } else {
                        "age_screen"
                    }

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable("age_screen") {
                            AgeSelectionScreen(context, navController)
                        }

                        composable("baseScreen") {
                            BaseScreen(context, navController)
                        }

                        composable("wallpaper_home_screen") { navBackStackEntry ->
                            val wall = navController.previousBackStackEntry
                                ?.savedStateHandle?.get<Wall>("wall_data")
                            val category = navController.previousBackStackEntry
                                ?.savedStateHandle?.get<Category>("category_data")

                            if (wall != null && category != null) {
                                WallpaperHomeScreen(
                                    wall = wall,
                                    category = category,
                                    onFavoriteClick = {},
                                    onShareClick = {},
                                    onDownloadClick = {}
                                )
                            }
                        }
                        composable("wallpaper_more_screen") {
//                            val wall = navController.previousBackStackEntry
//                                ?.savedStateHandle?.get<Wall>("wall_data")
                            val category = navController.previousBackStackEntry
                                ?.savedStateHandle?.get<Category>("category_data")

                            Log.d("LogTag", " 12313 ${category.toString()}")
                            if (category != null) {
                                MoreWallpaperScreen(
                                    category = category,
                                    onBackClick = { navController.popBackStack() },
                                    navController = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(context: Context, navController: NavController) {
    val navType = rememberSaveable { mutableStateOf(MovieNavType.SHOWING) }

    val viewModel: WallpapersHomeViewModel = viewModel(
        factory = WallpapersHomeViewModelFactory(context)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column {
                MoviesBottomBar(navType)

//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(80.dp)
//                        .background(Color.LightGray),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "Quảng cáo", color = Color.Black)
//                }
            }
        }
    ) { paddingValues -> // <--- nhận padding từ Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            Crossfade(
                targetState = navType.value,
                modifier = Modifier.weight(1f),
                label = ""
            ) { navTypeState ->
                when (navTypeState) {
                    MovieNavType.SHOWING -> HomeScreen(navController, viewModel)
                    MovieNavType.TRENDING -> TrendingScreen(
                        categories = viewModel.categories,
                        navController = navController
                    )

                    MovieNavType.PROFILE -> ProfileScreen()
                }
            }
        }
    }

}


@Composable
fun AgeSelectionScreen(context: Context, navController: NavController) {
    Box(
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = ColorPrimary
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tuổi của bạn",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Việc chọn độ tuổi sẽ giúp chúng tôi gợi ý những hình nền tuyệt đẹp phù hợp với sở thích của bạn",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            val ageOptions = listOf(
                "Dưới 18", "18 – 24", "25 – 34", "35 – 44", "Trên 45"
            )

            ageOptions.forEach { option ->
                Button(
                    onClick = {
                        val sharedPrefs =
                            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                        val editor = sharedPrefs.edit()
                        editor.putString("age_user", option).apply()
                        navController.navigate("baseScreen") {
                            popUpTo("age_screen") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        text = option,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WallpaperTheme {
        AgeSelectionScreen(LocalContext.current, rememberNavController())
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BasePreview() {
//    WallpaperTheme {
//        BaseScreen()
//    }
//}