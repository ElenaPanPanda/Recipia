package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.core.ui.components.AppOutlinedButton
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkRed
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem
import com.examplerecipia.feature.groceries.impl.ui.GroceriesEvent
import com.examplerecipia.feature.groceries.impl.ui.GroceriesState

@Composable
fun GroceriesContent(
    state: GroceriesState.Success,
    event: (GroceriesEvent) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
    ) {
        GroceriesTopBar(onShareClicked = { })
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppInputField(
                    value = state.newItemValue,
                    onValueChange = { value -> event(GroceriesEvent.OnNewItemValueChange(value)) },
                    hint = stringResource(uiR.string.core_ui_add_item_to_list),
                    modifier = Modifier.weight(1f),
                    keyboardActions = KeyboardActions(
                        onDone = { event(GroceriesEvent.OnAddNewItem) }
                    )
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = Icons.add),
                    contentDescription = stringResource(uiR.string.core_ui_add),
                    tint = snowWhite,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MediumTeal)
                        .padding(4.dp)
                        .clickable(onClick = { event(GroceriesEvent.OnAddNewItem) })
                        .size(24.dp)
                )
            }

            if (state.shoppingList.isNotEmpty()) {
                state.shoppingList.forEachIndexed { index, shoppingListItem ->
                    ShoppingListSection(
                        shoppingListItem = shoppingListItem,
                        onRemoveClicked = { event(GroceriesEvent.OnRemoveListBlock(index)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }

            Row(modifier = Modifier.padding(vertical = 24.dp)) {
                AppOutlinedButton(
                    text = "Clear Checked",
                    onClick = { },
                    contentColor = DarkBlue.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                AppOutlinedButton(
                    text = "Clear All",
                    onClick = { },
                    contentColor = DarkRed.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceriesContentPreview() {
    GroceriesContent(
        state = GroceriesState.Success(
            listOf(
                ShoppingListItem(
                    title = "Other Items",
                    ingredientsList = listOf(
                        ShoppingListIngredient(
                            amount = "",
                            name = "Apples",
                            isCrossedOut = false,
                        ),
                    )
                ),
                ShoppingListItem(
                    title = "Golden Chickpea & Spinach Curry",
                    ingredientsList = listOf(
                        ShoppingListIngredient(
                            amount = "1 can (13.5 oz)",
                            name = "Coconut milk (full-fat)",
                            isCrossedOut = true,
                        ),
                        ShoppingListIngredient(
                            amount = "2 tbsp",
                            name = "Curry powder",
                            isCrossedOut = false,
                        ),
                        ShoppingListIngredient(
                            amount = "1 cup",
                            name = "Spinach",
                            isCrossedOut = false,
                        ),
                    )
                ),
                ShoppingListItem(
                    title = "Spiced Pumpkin & Lentil Stew",
                    ingredientsList = listOf(
                        ShoppingListIngredient(
                            amount = "1 cup",
                            name = "Red lentils, rinsed",
                            isCrossedOut = false,
                        ),
                        ShoppingListIngredient(
                            amount = "3 cloves",
                            name = "Garlic, minced",
                            isCrossedOut = false,
                        ),
                        ShoppingListIngredient(
                            amount = "1",
                            name = "medium Butternut Pumpkin",
                            isCrossedOut = false,
                        ),
                    )
                ),
                ShoppingListItem(
                    title = "Spiced Pumpkin & Lentil Stew",
                    ingredientsList = listOf(
                        ShoppingListIngredient(
                            amount = "1 cup",
                            name = "Red lentils, rinsed",
                            isCrossedOut = false,
                        ),
                        ShoppingListIngredient(
                            amount = "3 cloves",
                            name = "Garlic, minced",
                            isCrossedOut = true,
                        ),
                        ShoppingListIngredient(
                            amount = "1",
                            name = "medium Butternut Pumpkin",
                            isCrossedOut = false,
                        ),
                    )
                ),
            )
        ),
        event = {},
    )
}