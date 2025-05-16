package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.TextMuted

@Composable
fun IngredientTitleInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidthPx = 1.dp.toPx()
                val y = size.height - strokeWidthPx / 2

                if (isFocused) {
                    drawLine(
                        color = MediumTeal,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidthPx
                    )
                } else {
                    drawLine(
                        color = LightTeal,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
            .padding(vertical = 8.dp),
        textStyle = AppTypography().poppinsSemiBold.copy(fontSize = 15.2.sp, color = DarkBlue),
        singleLine = true,
        cursorBrush = SolidColor(DarkTeal),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = AppTypography().poppinsSemiBold.copy(fontSize = 15.2.sp),
                        color = TextMuted.copy(alpha = 0.8f),
                        modifier = Modifier.padding(start = 0.dp)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomIngredientGroupTitleInputPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        IngredientTitleInputField(
            value = "",
            onValueChange = { },
            hint = "Optional: Group title (e.g., Dough)",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        IngredientTitleInputField(
            value = "For the Cake Batter",
            hint = "Optional: Group title (e.g., Dough)",
            onValueChange = { },
        )
    }
}