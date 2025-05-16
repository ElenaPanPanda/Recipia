package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppTitle
import com.example.recipia.core.ui.components.OutlinedButton
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.feature.addrecipe.impl.R

@Composable
fun AddIngredientsSection(
    ingredients: List<IngredientSection>,
    onIngredientTitleValueChange: (String, Int) -> Unit,
    onIngredientNameValueChange: (String, Int, Int) -> Unit,
    onIngredientAmountValueChange: (String, Int, Int) -> Unit,
    onIngredientRemoveClicked: (Int, Int) -> Unit,
    onAddIngredientClicked: (Int) -> Unit,
    onRemoveIngredientsCardClicked: (Int) -> Unit,
    onAddIngredientsGroupClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        AppTitle(
            text = stringResource(id = uiR.string.core_ui_ingredients),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        ingredients.forEachIndexed { index, ingredientSection ->
            if (index > 0) Spacer(modifier = Modifier.height(24.dp))

            IngredientsSectionCard(
                ingredientSection = ingredientSection,
                onIngredientTitleValueChange = { onIngredientTitleValueChange(it, index) },
                onIngredientNameValueChange = { value, ingredientIndex -> onIngredientNameValueChange(value, index, ingredientIndex) },
                onIngredientAmountValueChange = { value, ingredientIndex -> onIngredientAmountValueChange(value, index, ingredientIndex) },
                onIngredientRemoveClicked = { ingredientIndex -> onIngredientRemoveClicked(index, ingredientIndex) },
                onAddIngredientClicked = { onAddIngredientClicked(index) },
                onRemoveCardClicked = { onRemoveIngredientsCardClicked(index) },
            )
        }

        OutlinedButton(
            text = stringResource(R.string.add_recipe_add_ingredients_group),
            onClick = onAddIngredientsGroupClicked,
            leadingIcon = ImageVector.vectorResource(Icons.add),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddIngredientsSectionPreview() {
    AddIngredientsSection(
        ingredients = listOf(
            IngredientSection(
                title = "",
                ingredientsList = listOf(
                    Ingredient(amount = "300g", name = "Potato"),
                    Ingredient(amount = "", name = ""),
                )
            ),
            IngredientSection(
                title = "For baking:",
                ingredientsList = listOf(
                    Ingredient(amount = "300g", name = "Potato"),
                    Ingredient(amount = "", name = ""),
                )
            ),
        ),
        onIngredientTitleValueChange = { _, _ -> },
        onIngredientNameValueChange = { _, _, _ -> },
        onIngredientAmountValueChange = { _, _, _ -> },
        onIngredientRemoveClicked = { _, _ -> },
        onAddIngredientClicked = { },
        onRemoveIngredientsCardClicked = { },
        onAddIngredientsGroupClicked = { },
        modifier = Modifier.padding(16.dp)
    )
}