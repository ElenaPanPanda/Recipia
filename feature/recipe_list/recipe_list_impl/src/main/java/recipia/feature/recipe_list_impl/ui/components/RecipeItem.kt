package recipia.feature.recipe_list_impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun RecipeItem(
    title: String,
    imageUrl: String,
    placeholderColor: Color,
    rating: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var displayFallback by remember(imageUrl) { mutableStateOf(imageUrl.isBlank()) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = snowWhite),
        onClick = onClick
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            if (displayFallback) {
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .fillMaxHeight()
                        .background(placeholderColor)
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.recipes),
                        contentDescription = null,
                        tint = snowWhite,
                        modifier = Modifier.size(32.dp),
                    )
                }
            } else {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .width(120.dp)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        ),
                    contentScale = ContentScale.Crop,
                    onError = { displayFallback = true },
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = AppTypography().playDisplayBold.copy(
                        fontSize = 18.sp,
                        lineHeight = 25.sp,
                    ),
                    color = DarkBlue,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                StarRatingDisplay(rating = rating)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemPreview() {
    RecipeItem(
        title = "Apple & Cinnamon Crumble Bars",
        imageUrl = "",
        placeholderColor = MediumTeal,
        rating = 5,
        modifier = Modifier.padding(8.dp),
        onClick = {},
    )
}