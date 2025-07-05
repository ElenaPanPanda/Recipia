package recipia.feature.impl.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun CategoryChipFilled(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isSelected) MediumTeal else snowWhite
    val textColor = if (isSelected) snowWhite else MediumTeal
    val borderColor = if (isSelected) MediumTeal else LightTeal
    val elevation = if (isSelected) 4.dp else 0.dp
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(12.dp),
                ambientColor = if (isSelected) MediumTeal.copy(alpha = 0.3f) else Color.Transparent,
                spotColor = if (isSelected) MediumTeal.copy(alpha = 0.3f) else Color.Transparent
            )
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
            .padding(
                horizontal = 16.dp,
                vertical = 6.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = AppTypography().poppinsMedium.copy(
                color = textColor,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipFilledPreview() {
    Row(modifier = Modifier.padding(16.dp)) {
        CategoryChipFilled(
            text = "Breakfast",
            isSelected = true,
            onClick = {}
        )
        Spacer(modifier = Modifier.width(8.dp))
        CategoryChipFilled(
            text = "Lunch",
            isSelected = false,
            onClick = {}
        )
    }
}