import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val categoryId: String = "",
    val categoryDes: String = "",
    val categoryThumb: String = "",
    val wallList: List<Wall> = emptyList()
) : Parcelable
