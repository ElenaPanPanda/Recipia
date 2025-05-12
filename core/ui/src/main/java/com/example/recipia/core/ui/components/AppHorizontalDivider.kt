package com.example.recipia.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.theme.dividerColor

@Composable
fun AppHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = dividerColor,
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun AppHorizontalDividerPreview() {
    AppHorizontalDivider(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 80.dp
        )
    )
}