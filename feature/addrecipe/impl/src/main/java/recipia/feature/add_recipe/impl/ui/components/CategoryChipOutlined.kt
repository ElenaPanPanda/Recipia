package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.R
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.MediumTeal

@Composable
fun CategoryChipOutlined(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
)  {
    val backgroundColor = if (isSelected) MediumTeal.copy(alpha = 0.1f) else Color.Transparent
    val textColor = if (isSelected) DarkTeal else MediumTeal
    val borderColor = if (isSelected) MediumTeal else LightTeal
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(
                border = BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.check),
                    contentDescription = stringResource(R.string.core_ui_selected),
                    tint = textColor,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                style = AppTypography().poppinsMedium.copy(
                    color = textColor,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipOutlinedPreview() {
    Row(modifier = Modifier.padding(16.dp)) {
        CategoryChipOutlined(
            text = "Breakfast",
            isSelected = true,
            onClick = {},
            modifier = Modifier.padding(end = 8.dp)
        )
        CategoryChipOutlined(
            text = "Lunch",
            isSelected = false,
            onClick = {}
        )
    }
}