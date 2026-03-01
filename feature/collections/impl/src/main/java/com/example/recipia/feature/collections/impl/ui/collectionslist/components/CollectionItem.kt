package com.example.recipia.feature.collections.impl.ui.collectionslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.Disabled
import com.example.recipia.core.ui.theme.TextMuted
import com.example.recipia.feature.collections.impl.R

@Composable
fun CollectionItem(
    title: String,
    amountOfRecipes: Int,
    collectionColor: PlaceholderColor,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(vertical = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 12.dp, vertical = 2.dp)
                .fillMaxHeight()
                .width(4.5.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(collectionColor.color)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = AppTypography().playDisplayBold.copy(fontSize = 19.sp),
                color = DarkBlue,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = stringResource(R.string.collections_amount_of_recipes, amountOfRecipes),
                style = AppTypography().poppinsNormal.copy(fontSize = 13.sp),
                color = TextMuted
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = Icons.chevronRight),
            contentDescription = stringResource(id = R.string.collections_navigate_to_collection),
            tint = Disabled,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(4.dp)
                .size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionItemPreview() {
    CollectionItem(
        title = "Favorites",
        amountOfRecipes = 12,
        collectionColor = PlaceholderColor.DARK_RED,
        onItemClick = {},
    )
}