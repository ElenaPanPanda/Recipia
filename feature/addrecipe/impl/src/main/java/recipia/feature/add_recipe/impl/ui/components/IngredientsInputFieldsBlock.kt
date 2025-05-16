package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.R
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.DarkRed
import com.example.recipia.core.ui.theme.LightTeal

@Composable
fun IngredientsInputFieldsBlock(
    ingredientValue: String,
    onIngredientValueChange: (String) -> Unit,
    amountValue: String,
    onAmountValueChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)) {
        VerticalDivider(
            thickness = 1.5.dp,
            color = LightTeal,
            modifier = Modifier.padding(end = 8.dp)
        )
        Column {
            AppInputField(
                value = ingredientValue,
                onValueChange = onIngredientValueChange,
                hint = "Ingredient name", // TODO: Move to local res
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppInputField(
                    value = amountValue,
                    onValueChange = onAmountValueChange,
                    hint = "Amount (e.g., 1 cup, 100g)", // TODO: Move to local res
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.cancel),
                    contentDescription = stringResource(R.string.core_ui_cancel),
                    tint = DarkRed,
                    modifier = Modifier
                        .clickable(onClick = onRemoveClick)
                        .padding(4.dp)
                        .size(20.dp)
                )
            }
        }
    }
}