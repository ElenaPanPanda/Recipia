package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.components.AppOutlinedButton
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkRed
import com.examplerecipia.feature.groceries.impl.R

@Composable
fun ClearButtons(
    onClearCheckedClicked: () -> Unit,
    onClearAllClicked: () -> Unit,
    modifier : Modifier = Modifier,
) {
    Row(modifier = modifier.padding(vertical = 24.dp)) {
        AppOutlinedButton(
            text = stringResource(R.string.groceries_clear_checked),
            onClick = onClearCheckedClicked,
            contentColor = DarkBlue.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        AppOutlinedButton(
            text = stringResource(R.string.groceries_clear_all),
            onClick = onClearAllClicked,
            contentColor = DarkRed.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ClearButtonsPreview() {
    ClearButtons(
        onClearCheckedClicked = {},
        onClearAllClicked = {},
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}