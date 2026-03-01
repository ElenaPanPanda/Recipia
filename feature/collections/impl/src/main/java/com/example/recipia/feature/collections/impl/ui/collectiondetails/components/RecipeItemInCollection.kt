package com.example.recipia.feature.collections.impl.ui.collectiondetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.ui.R
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun RecipeItemInCollection(
    recipe: ShortRecipe,
    onClick: () -> Unit,
    onDeleteRecipeClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var displayFallback by remember(recipe.imageUrl) { mutableStateOf(recipe.imageUrl.isBlank()) }

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
                        .background(recipe.placeholderColor.color)
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(Icons.pan),
                        contentDescription = null,
                        tint = snowWhite,
                        modifier = Modifier.size(32.dp),
                    )
                }
            } else {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
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
            Text(
                text = recipe.title,
                style = AppTypography().playDisplayBold.copy(
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                ),
                color = DarkBlue,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.cancel),
                contentDescription = stringResource(id = R.string.core_ui_delete),
                tint = DarkTeal,
                modifier = Modifier
                    .clickable(onClick = onDeleteRecipeClicked)
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemInCollectionPreview() {
    RecipeItemInCollection(
        recipe = ShortRecipe(
            id = "id",
            title = "Apple & Cinnamon Crumble Bars",
            imageUrl = "",
            placeholderColor = PlaceholderColor.DARK_RED,
            rating = 5,
        ),
        onClick = {},
        onDeleteRecipeClicked = {},
    )
}