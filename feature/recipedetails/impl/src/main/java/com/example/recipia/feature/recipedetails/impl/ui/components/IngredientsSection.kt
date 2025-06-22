package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.components.IconTextButton
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection

@Composable
fun IngredientsSection(
    ingredients: List<DetailedIngredientSection>,
    isAllIngredientsChecked: Boolean,
    onAddIngredient: (DetailedIngredient) -> Unit,
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

            if (isAllIngredientsChecked) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = Icons.allDone),
                    contentDescription = null,
                    tint = DarkTeal,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            } else {
                IconTextButton(
                    text = stringResource(id = uiR.string.core_ui_add_all),
                    icon = ImageVector.vectorResource(id = Icons.shoppingCartAdd),
                    onClick = onAddAllIngredients,
                )
            }
        }

        ingredients.forEachIndexed { sectionIndex, ingredientSection ->
            Column {
                if (!ingredientSection.title.isNullOrEmpty()) {
                    Text(
                        text = ingredientSection.title,
                        style = AppTypography().playDisplayBold.copy(fontSize = 17.sp),
                        color = DarkTeal,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    AppHorizontalDivider()
                }
                ingredientSection.ingredientsList.forEachIndexed { ingredientIndex, ingredient ->
                    if (sectionIndex > 0 &&
                        ingredientIndex == 0 &&
                        ingredientSection.title.isNullOrEmpty()
                    ) {
                        AppHorizontalDivider()
                    }
                    IngredientItem(
                        ingredient = ingredient,
                        onAddClick = { onAddIngredient(ingredient) }
                    )
                    if (ingredientIndex < ingredientSection.ingredientsList.size - 1) {
                        AppHorizontalDivider()
                    }
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
            DetailedIngredientSection(
                title = "For cake",
                ingredientsList = listOf(
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
                )
            ),
            DetailedIngredientSection(
                title = "For butter",
                ingredientsList = listOf(
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
                )
            )
        ),
        isAllIngredientsChecked = false,
        onAddIngredient = {},
        onAddAllIngredients = {},
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemWithoutTitlesPreview() {
    IngredientsSection(
        ingredients = listOf(
            DetailedIngredientSection(
                ingredientsList = listOf(
                    DetailedIngredient(
                        amount = "100g",
                        ingredient = "Apples (Granny Smith or Honeycrisp), peeled, cored, and sliced",
                        addedToList = false,
                    ),
                    DetailedIngredient(
                        amount = "1 medium",
                        ingredient = "Butternut Pumpkin, peeled & cubed",
                        addedToList = true,
                    )
                )
            ),
            DetailedIngredientSection(
                ingredientsList = listOf(
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
                )
            )
        ),
        isAllIngredientsChecked = false,
        onAddIngredient = {},
        onAddAllIngredients = {},
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemWithOnlySecondTitlePreview() {
    IngredientsSection(
        ingredients = listOf(
            DetailedIngredientSection(
                ingredientsList = listOf(
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
                )
            ),
            DetailedIngredientSection(
                title = "For cake",
                ingredientsList = listOf(
                    DetailedIngredient(
                        amount = "100g",
                        ingredient = "Apples (Granny Smith or Honeycrisp), peeled, cored, and sliced",
                        addedToList = false,
                    ),
                    DetailedIngredient(
                        amount = "1 medium",
                        ingredient = "Butternut Pumpkin, peeled & cubed",
                        addedToList = true,
                    )
                )
            )
        ),
        isAllIngredientsChecked = true,
        onAddIngredient = {},
        onAddAllIngredients = {},
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemWithOneSectionWithoutTitlePreview() {
    IngredientsSection(
        ingredients = listOf(
            DetailedIngredientSection(
                ingredientsList = listOf(
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
                )
            ),
        ),
        isAllIngredientsChecked = false,
        onAddIngredient = {},
        onAddAllIngredients = {},
        modifier = Modifier.padding(16.dp)
    )
}