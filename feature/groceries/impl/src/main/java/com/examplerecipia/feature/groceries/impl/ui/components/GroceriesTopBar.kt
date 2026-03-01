package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.LighterBeige

@Composable
fun GroceriesTopBar(
    onShareClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(LighterBeige.copy(alpha = 0.8f)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = uiR.string.core_ui_shopping_list),
                style = AppTypography().playDisplayBold.copy(fontSize = 22.sp),
                color = DarkTeal,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.share),
                contentDescription = stringResource(id = uiR.string.core_ui_cancel),
                tint = DarkTeal,
                modifier = Modifier
                    .clickable(onClick = onShareClicked)
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        AppHorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceriesTopBarPreview() {
    GroceriesTopBar(onShareClicked = {})
}