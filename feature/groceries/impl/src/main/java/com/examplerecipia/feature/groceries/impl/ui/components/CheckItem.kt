package com.examplerecipia.feature.groceries.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.TextMuted
import com.example.recipia.core.ui.theme.snowWhite
import com.examplerecipia.feature.groceries.impl.domain.model.ShoppingListIngredient

@Composable
fun CheckItem(
    ingredient: ShoppingListIngredient,
    onCheckedChange: (ShoppingListIngredient) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        Checkbox(
            checked = ingredient.isCrossedOut,
            onCheckedChange = { onCheckedChange(ingredient) },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = MediumTeal,
                uncheckedColor = MediumTeal,
                checkmarkColor = snowWhite
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "${ingredient.amount} ${ingredient.name}",
            style = AppTypography().poppinsNormal.copy(
                fontSize = 14.4.sp,
                textDecoration = if (ingredient.isCrossedOut) TextDecoration.LineThrough else TextDecoration.None
            ),
            color = if (ingredient.isCrossedOut) TextMuted else DarkBlue,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckItemPreview() {
    Column(modifier = Modifier.padding(8.dp)) {
        CheckItem(
            ingredient = ShoppingListIngredient(
                amount = "1 can (13.5 oz)",
                name = "Coconut milk (full-fat)",
                isCrossedOut = true,
            ),
            onCheckedChange = {}
        )
        CheckItem(
            ingredient = ShoppingListIngredient(
                amount = "2 tbsp",
                name = "Curry powder",
                isCrossedOut = false,
            ),
            onCheckedChange = {}
        )
        CheckItem(
            ingredient = ShoppingListIngredient(
                amount = "1 can (13.5 oz)",
                name = "Spinach Coconut milk (full-fat) Coconut milk (full-fat)",
                isCrossedOut = false,
            ),
            onCheckedChange = {}
        )
    }
}