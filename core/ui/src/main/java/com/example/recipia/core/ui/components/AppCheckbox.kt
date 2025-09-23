package com.example.recipia.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.core.ui.utils.NoRippleInteractionSource

@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier
            .clip(shape = RoundedCornerShape(8))
            .size(20.dp),
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MediumTeal,
            uncheckedColor = MediumTeal,
            checkmarkColor = snowWhite
        ),
        interactionSource = remember { NoRippleInteractionSource },
    )
}

@Preview(showBackground = true)
@Composable
private fun AppCheckboxPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        AppCheckbox(
            checked = true,
            onCheckedChange = {},
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppCheckbox(
            checked = false,
            onCheckedChange = {},
        )
    }
}