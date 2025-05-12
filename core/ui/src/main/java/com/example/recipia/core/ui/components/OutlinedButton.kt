package com.example.recipia.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.MediumTeal

@Composable
fun OutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MediumTeal,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
        ),
        border = BorderStroke(2.dp, contentColor),
        enabled = enabled,
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 8.dp)
            )
        }
        Text(
            text = text,
            style = AppTypography().poppinsSemiBold.copy(fontSize = 16.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedButtonPreview() {
    OutlinedButton(
        text = "Start Cooking",
        onClick = {},
        leadingIcon = ImageVector.vectorResource(id = Icons.cutlery),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    )
}