package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient

@Composable
fun IngredientItem(
    ingredient: DetailedIngredient,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = ingredient.ingredient,
                style = AppTypography().poppinsNormal.copy(fontSize = 14.4.sp),
                color = DarkBlue
            )
            Text(
                text = ingredient.amount,
                style = AppTypography().poppinsMedium.copy(fontSize = 13.6.sp),
                color = DarkTeal
            )
        }
        IconButton(
            onClick = onAddClick,
            modifier = Modifier.size(32.dp),
            colors = IconButtonDefaults.iconButtonColors(contentColor = MediumTeal)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (ingredient.addedToList) Icons.check
                    else Icons.shoppingCartAdd
                ),
                contentDescription = "Add ${ingredient.ingredient}",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        IngredientItem(
            ingredient = DetailedIngredient(
                amount = "100g",
                ingredient = "Potatoes",
                addedToList = false
            ),
            onAddClick = {}
        )
        IngredientItem(
            ingredient = DetailedIngredient(
                amount = "1 medium",
                ingredient = "Butternut Pumpkin, peeled & cubed",
                addedToList = true
            ),
            onAddClick = {}
        )
    }
}