import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wall(
    val wallId: String = "",
    val type: Int = 0,
    val wallThumb: String = "",
    val wallHashtag: String = "",
    val dataSet: String = "",
    val isPremium: Boolean = false
) : Parcelable
