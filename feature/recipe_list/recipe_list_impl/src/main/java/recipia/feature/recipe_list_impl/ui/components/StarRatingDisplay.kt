package recipia.feature.recipe_list_impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.GoldOrange
import com.example.recipia.core.ui.theme.StarEmpty
import com.example.recipia.core.ui.theme.TextMuted

@Composable
fun StarRatingDisplay(
    rating: Int,
    modifier: Modifier = Modifier,
) {
    val actualRating = rating / 2.0f
    val numStars = 5

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..numStars) {
            val starIcon = when {
                actualRating >= i -> Icons.starFilled
                actualRating >= i - 0.5f -> Icons.starHalf
                else -> Icons.starEmpty
            }
            val tintColor = if (starIcon == Icons.starEmpty) StarEmpty else GoldOrange

            Icon(
                imageVector = ImageVector.vectorResource(starIcon),
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier.size(14.4.dp)
            )
            if (i < numStars) {
                Spacer(modifier = Modifier.width(2.dp))
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "%.1f".format(actualRating),
            style = AppTypography().poppinsMedium.copy(
                color = TextMuted,
                fontSize = 12.8.sp,
                lineHeight = 14.sp,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StarRatingDisplayPreview() {
    Column(modifier = Modifier.padding(8.dp)) {
        StarRatingDisplay(rating = 0)
        StarRatingDisplay(rating = 5)
        StarRatingDisplay(rating = 8)
        StarRatingDisplay(rating = 10)
    }
}