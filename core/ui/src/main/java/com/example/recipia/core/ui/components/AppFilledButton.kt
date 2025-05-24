package com.example.recipia.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkRed
import com.example.recipia.core.ui.theme.DarkTeal

@Composable
fun AppFilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = DarkTeal,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = contentColor),
        enabled = enabled,
    ) {
        Text(
            text = text,
            style = AppTypography().poppinsSemiBold.copy(fontSize = 16.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppFilledButtonPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        AppFilledButton(
            text = "Start Cooking",
            onClick = {},
            modifier = Modifier.padding(24.dp)
        )
        AppFilledButton(
            text = "Start Cooking",
            onClick = {},
            modifier = Modifier.padding(24.dp),
            enabled = false
        )
        AppFilledButton(
            text = "Delete",
            onClick = {},
            modifier = Modifier.padding(24.dp),
            contentColor = DarkRed
        )
    }
}