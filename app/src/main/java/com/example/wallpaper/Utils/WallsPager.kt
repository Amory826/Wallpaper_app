import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallpaper.model.WallpapersHomeViewModel
import com.example.wallpaper.model.WallpapersHomeViewModelFactory
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.wallpaper.Utils.WallPagerItem
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WallsPager() {
    val viewModel: WallpapersHomeViewModel =
        viewModel(factory = WallpapersHomeViewModelFactory(LocalContext.current))

    val walls by viewModel.wallList.collectAsState()

    if (walls.isNotEmpty()) {
        val pagerState = rememberPagerState(initialPage = 0)

        SnapPosition.Center
        Column {

            HorizontalPager(
                count = walls.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            ) { page ->
                WallPagerItem(wall = walls[page])
            }
        }
    } else {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}
