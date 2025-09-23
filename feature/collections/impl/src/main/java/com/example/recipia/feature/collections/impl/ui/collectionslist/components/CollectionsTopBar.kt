package com.example.recipia.feature.collections.impl.ui.collectionslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LighterBeige
import com.example.recipia.feature.collections.impl.R

@Composable
fun CollectionsTopBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(LighterBeige.copy(alpha = 0.8f)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp)
        ) {
            Text(
                text = stringResource(R.string.collections_screen_title),
                style = AppTypography().playDisplayBold.copy(fontSize = 22.sp),
                color = DarkTeal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        AppHorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionsTopBarPreview() {
    CollectionsTopBar()
}