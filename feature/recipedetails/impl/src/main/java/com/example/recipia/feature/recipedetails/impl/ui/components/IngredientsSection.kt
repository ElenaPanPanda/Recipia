package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient

@Composable
fun IngredientsSection(
    ingredients: List<DetailedIngredient>,
    onAddIngredient: (Int) -> Unit,
    onAddAllIngredients: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = uiR.string.core_ui_ingredients),
                style = AppTypography().poppinsSemiBold.copy(fontSize = 18.sp),
                color = DarkTeal
            )

            AddAllButton(
                text = stringResource(id = uiR.string.core_ui_add_all),
                onClick = onAddAllIngredients,
            )
        }
        Column {
            ingredients.forEachIndexed { index, ingredient ->
                IngredientItem(
                    ingredient = ingredient,
                    onAddClick = { onAddIngredient(index) }
                )
                if (index < ingredients.size - 1) {
                    AppHorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IngredientsSectionPreview() {
    IngredientsSection(
        ingredients = listOf(
            DetailedIngredient(
                amount = "100g",
                ingredient = "Potatoes",
                addedToList = false,
            ),
            DetailedIngredient(
                amount = "1 medium",
                ingredient = "Butternut Pumpkin, peeled & cubed",
                addedToList = true,
            )
        ),
        onAddIngredient = {},
        onAddAllIngredients = {}
    )
}