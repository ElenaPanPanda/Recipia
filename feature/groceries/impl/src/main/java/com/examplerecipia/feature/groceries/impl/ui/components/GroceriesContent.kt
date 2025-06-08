package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.snowWhite
import com.examplerecipia.feature.groceries.impl.R
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
                .fillMaxHeight()
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
                        onDone = { if (state.newItemValue.isNotBlank()) event(GroceriesEvent.OnAddNewItem) }
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
                state.shoppingList.forEachIndexed { shoppingListItemIndex, shoppingListItem ->
                    ShoppingListSection(
                        shoppingListItem = shoppingListItem,
                        onCheckChanged = { ingredientIndex ->
                            event(
                                GroceriesEvent.OnCheckChanged(
                                    shoppingListItemIndex,
                                    ingredientIndex
                                )
                            )
                        },
                        onRemoveClicked = {
                            event(
                                GroceriesEvent.OnRemoveListBlock(
                                    shoppingListItemIndex
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ClearButtons(
                    onClearCheckedClicked = { },
                    onClearAllClicked = { },
                )
            } else {
                Text(
                    text = stringResource(R.string.groceries_empty_list),
                    color = DarkBlue.copy(alpha = 0.6f),
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center,
                    style = AppTypography().poppinsSemiBold.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
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
            )
        ),
        event = {},
    )
}