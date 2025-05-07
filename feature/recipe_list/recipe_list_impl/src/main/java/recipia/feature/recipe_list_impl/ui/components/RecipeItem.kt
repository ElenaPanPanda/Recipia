package recipia.feature.recipe_list_impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import com.example.recipia.core.ui.components.Like
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.core.ui.theme.softBlack

@Composable
fun RecipeItem(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = snowWhite)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )
                Like(
                    isLiked = isFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = Typography().labelLarge,
                color = softBlack,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemPreview() {
    Row(modifier = Modifier.padding(8.dp)) {
        RecipeItem(
            title = "Title",
            imageUrl = "",
            isFavorite = true,
        )
        Spacer(modifier = Modifier.width(8.dp))
        RecipeItem(
            title = "Title",
            imageUrl = "",
            isFavorite = false,
        )
    }
}