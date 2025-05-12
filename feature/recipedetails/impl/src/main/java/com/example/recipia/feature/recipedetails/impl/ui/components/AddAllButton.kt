package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.BurntOrange

@Composable
fun AddAllButton(
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = Icons.cartPlus),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .padding(end = 4.dp)
                .align(Alignment.CenterVertically),
            tint = BurntOrange
        )

        Text(
            text = text,
            style = AppTypography().poppinsSemiBold.copy(fontSize = 12.sp),
            color = BurntOrange,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAllButtonPreview() {
    AddAllButton(
        text = "Add All",
        onClick = {},
    )
}