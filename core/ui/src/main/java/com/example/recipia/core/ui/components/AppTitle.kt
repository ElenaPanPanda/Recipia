package com.example.recipia.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal

@Composable
fun AppTitle(
    text: String,
    modifier : Modifier = Modifier
) {
    Text(
        text = text,
        style = AppTypography().playDisplayBold.copy(fontSize = 17.6.sp),
        color = DarkTeal,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTitlePreview() {
    AppTitle(
        text = "Title",
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    )
}