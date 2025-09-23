package com.example.recipia.feature.recipedetails.impl.ui.bottomsheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppCheckbox
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.TextMuted

@Composable
fun CollectionCheckboxRow(
    collectionName: String,
    collectionSize: Int,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppCheckbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
        )
        Text(
            text = collectionName,
            style = AppTypography().poppinsNormal.copy(fontSize = 14.5.sp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )
        Text(
            text = "($collectionSize)",
            style = AppTypography().poppinsNormal.copy(fontSize = 13.sp, color = TextMuted),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionCheckboxRowPreview() {
    Column {
        CollectionCheckboxRow(
            collectionName = "Collection name",
            collectionSize = 10,
            isSelected = true,
            onCheckedChange = {},
            modifier = Modifier.padding(12.dp)
        )
        CollectionCheckboxRow(
            collectionName = "Very long and long and long and long and long and long and long and long and long and long and long collection name",
            collectionSize = 8,
            isSelected = false,
            onCheckedChange = {},
            modifier = Modifier.padding(12.dp)
        )
    }
}

