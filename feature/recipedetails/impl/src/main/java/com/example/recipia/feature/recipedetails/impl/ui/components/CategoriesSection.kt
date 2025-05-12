package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesSection(categories: List<RecipeCategory>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = uiR.string.core_ui_categories),
            style = AppTypography().poppinsSemiBold.copy(fontSize = 18.sp),
            color = DarkTeal,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                Text(
                    text = stringResource(category.textId),
                    color = DarkBlue,
                    style = AppTypography().poppinsMedium.copy(
                        fontSize = 12.sp,
                        letterSpacing = 0.3.sp
                    )
                )
            }
        }
    }
}