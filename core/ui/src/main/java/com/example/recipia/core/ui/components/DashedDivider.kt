package com.example.recipia.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.theme.DividerNavBarColor

// TODO: Make sure it is needed
@Composable
fun DashedDivider(
    modifier: Modifier = Modifier,
    color: Color = DividerNavBarColor
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 4.dp.toPx()), 0f)
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 1.dp.toPx(),
            pathEffect = pathEffect
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFBF9F3)
@Composable
fun PreviewDashedDivider() {
    Column {
        Text("Content above divider")
        DashedDivider(modifier = Modifier.padding(vertical = 12.dp))
        Text("Content below divider")
    }
}