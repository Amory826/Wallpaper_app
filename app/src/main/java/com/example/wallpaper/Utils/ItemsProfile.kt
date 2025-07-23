import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Giả định IconData là một class chứa ImageVector
data class IconData(val imageVector: ImageVector, val contentDescription: String? = null)

// Composable ItemProfile
@Composable
fun ItemProfile(
    iconData: IconData,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = iconData.imageVector,
                contentDescription = iconData.contentDescription,
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )
    }
}

// Ví dụ sử dụng
@Preview
@Composable
fun ProfileScreen() {
    Column {
        ItemProfile(
            iconData = IconData(Icons.Filled.Home, "Home Icon"),
            title = "Trang chủ",
            onClick = {
                // Thực hiện hành động khi bấm
                println("Item clicked")
            }
        )
        ItemProfile(
            iconData = IconData(Icons.Filled.Settings, "Settings Icon"),
            title = "Cài đặt",
            onClick = {
                // Thực hiện hành động khi bấm
                println("Item clicked")
            }
        )
    }
}