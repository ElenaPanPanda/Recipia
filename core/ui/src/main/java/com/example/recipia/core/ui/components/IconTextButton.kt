package com.example.recipia.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.BurntOrange
import com.example.recipia.core.ui.theme.MediumTeal

@Composable
fun IconTextButton(
    text: String,
    icon: ImageVector?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp,
    contentColor: Color = BurntOrange
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically),
                tint = contentColor
            )
        }

        Text(
            text = text,
            style = AppTypography().poppinsSemiBold.copy(fontSize = fontSize),
            color = contentColor,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAllButtonPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        IconTextButton(
            text = "Add All",
            icon = ImageVector.vectorResource(id = Icons.shoppingCartAdd),
            onClick = {},
        )
        IconTextButton(
            text = "Cancel",
            icon = null,
            onClick = {},
            fontSize = 16.sp,
            contentColor = MediumTeal
        )
    }
}