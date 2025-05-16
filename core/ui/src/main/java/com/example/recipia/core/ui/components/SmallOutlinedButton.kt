package com.example.recipia.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

// TODO: Make sure it is needed
@Composable
fun SmallOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MediumTeal,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
        ),
        border = BorderStroke(1.5.dp, contentColor),
        enabled = enabled
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
        }
        Text(
            text = text,
            style = AppTypography().poppinsSemiBold.copy(fontSize = 12.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallOutlinedButtonPreview() {
    Row (modifier = Modifier.padding(12.dp)) {
        SmallOutlinedButton(
            text = "Start Cooking",
            onClick = {},
            modifier = Modifier.padding(end = 8.dp)
        )
        SmallOutlinedButton(
            text = "Start Cooking",
            leadingIcon = ImageVector.vectorResource(id = Icons.add),
            onClick = {},
        )
    }
}