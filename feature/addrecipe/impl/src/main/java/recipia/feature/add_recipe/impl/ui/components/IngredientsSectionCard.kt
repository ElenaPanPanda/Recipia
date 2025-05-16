package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.ui.components.IconTextButton
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun IngredientsSectionCard(
    ingredientSection: IngredientSection,
    onIngredientTitleValueChange: (String) -> Unit,
    onIngredientRemoveClicked: (Int) -> Unit,
    onAddIngredientClicked: () -> Unit,
    onRemoveCardClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = snowWhite),
        border = BorderStroke(1.dp, LightTeal),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(Icons.trash),
            contentDescription = null,
            tint = DarkBlue.copy(alpha = 0.5f),
            modifier = Modifier
                .clickable(onClick = onRemoveCardClicked)
                .padding(top = 16.dp, end = 16.dp)
                .size(24.dp)
                .align(Alignment.End)
        )
        IngredientTitleInputField(
            value = ingredientSection.title ?: "",
            onValueChange = onIngredientTitleValueChange,
            hint = "Optional: Group title (e.g., Dough)",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
        ingredientSection.ingredientsList.forEachIndexed { index, ingredient ->
            IngredientsInputFieldsBlock(
                ingredientValue = ingredient.name,
                onIngredientValueChange = { onIngredientTitleValueChange(it) },
                amountValue = ingredient.amount,
                onAmountValueChange = { onIngredientTitleValueChange(it) },
                onRemoveClick = { onIngredientRemoveClicked(index) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        IconTextButton(
            text = "Add Ingredient", // TODO: Move to local res
            icon = ImageVector.vectorResource(Icons.add),
            onClick = onAddIngredientClicked,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IngredientsSectionCardPreview() {
    IngredientsSectionCard(
        ingredientSection = IngredientSection(
            title = "",
            ingredientsList = listOf(
                Ingredient(amount = "300g", name = "Potato"),
                Ingredient(amount = "", name = ""),
            ),
        ),
        onIngredientTitleValueChange = {},
        onIngredientRemoveClicked = {},
        onAddIngredientClicked = {},
        onRemoveCardClicked = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}