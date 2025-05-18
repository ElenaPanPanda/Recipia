package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.BurntOrange
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LighterBeige
import com.example.recipia.core.ui.theme.SheetHandle

@Composable
fun AddRecipeTopBar(
    saveEnabled: Boolean,
    onSaveEnabledClick: () -> Unit,
    onSaveDisabledClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = LighterBeige.copy(alpha = 0.8f),
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.cancel),
                contentDescription = stringResource(id = uiR.string.core_ui_cancel),
                tint = DarkTeal,
                modifier = Modifier
                    .clickable(onClick = onCancelClick)
                    .padding(8.dp)
                    .size(24.dp)
            )
            Text(
                text = stringResource(id = uiR.string.core_ui_add_new_recipe),
                style = AppTypography().playDisplayBold.copy(fontSize = 22.sp),
                color = DarkTeal,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            TextButton(
                onClick = if (saveEnabled) onSaveEnabledClick else onSaveDisabledClick,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                Text(
                    text = stringResource(id = uiR.string.core_ui_save),
                    style = AppTypography().poppinsSemiBold.copy(fontSize = 16.sp),
                    color = if (saveEnabled) BurntOrange else SheetHandle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddRecipeTopBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        AddRecipeTopBar(
            saveEnabled = true,
            onSaveEnabledClick = {},
            onSaveDisabledClick = {},
            onCancelClick = {},
        )
        Spacer(modifier = Modifier.height(12.dp))
        AddRecipeTopBar(
            saveEnabled = false,
            onSaveEnabledClick = {},
            onSaveDisabledClick = {},
            onCancelClick = {},
        )
    }
}