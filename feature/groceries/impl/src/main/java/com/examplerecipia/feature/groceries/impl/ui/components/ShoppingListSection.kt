package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.TextMuted
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListItem

@Composable
fun ShoppingListSection(
    shoppingListItem: ShoppingListItem,
    onRemoveClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = snowWhite,
        modifier = modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .animateContentSize(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                Text(
                    text = shoppingListItem.title,
                    style = AppTypography().playDisplayBold.copy(fontSize = 17.6.sp),
                    color = DarkTeal,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onRemoveClicked,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = Icons.trash),
                        contentDescription = stringResource(id = uiR.string.core_ui_delete),
                        tint = TextMuted.copy(alpha = 0.7f)
                    )
                }
            }
            AppHorizontalDivider(color = LightTeal, thickness = 1.5.dp)

            if (shoppingListItem.ingredientsList.isNotEmpty()) {
                shoppingListItem.ingredientsList.forEachIndexed { index, ingredient ->
                    CheckItem(
                        ingredient = ingredient,
                        onCheckedChange = {}
                    )
                    if (index < shoppingListItem.ingredientsList.lastIndex) {
                        AppHorizontalDivider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingListSectionPreview1() {
    ShoppingListSection(
        shoppingListItem = ShoppingListItem(
            title = "Golden Chickpea & Spinach Curry",
            ingredientsList = listOf(
                ShoppingListIngredient(
                    amount = "1 can (13.4)",
                    name = "Coconut milk (full-fat)",
                    isCrossedOut = true,
                ),
                ShoppingListIngredient(
                    amount = "2 tbsp",
                    name = "Curry powder",
                    isCrossedOut = false,
                ),
            )
        ),
        onRemoveClicked = {},
        modifier = Modifier.padding(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ShoppingListSectionPreview2() {
    ShoppingListSection(
        shoppingListItem = ShoppingListItem(
            title = "Golden Chickpea & Spinach Curry & Spiced Pumpkin & Lentil Stew",
            ingredientsList = listOf(
                ShoppingListIngredient(
                    amount = "1 can (13.4)",
                    name = "Coconut milk (full-fat)",
                    isCrossedOut = true,
                ),
                ShoppingListIngredient(
                    amount = "2 tbsp",
                    name = "Curry powder",
                    isCrossedOut = false,
                ),
            )
        ),
        onRemoveClicked = {},
        modifier = Modifier.padding(12.dp)
    )
}