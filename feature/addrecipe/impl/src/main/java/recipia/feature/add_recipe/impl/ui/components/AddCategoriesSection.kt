package recipia.feature.add_recipe.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppTitle
import recipia.feature.add_recipe.impl.domain.model.CategoryForChoose

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddCategoriesSection(
    categories: List<CategoryForChoose?>,
    onSelectedCategory: (CategoryForChoose) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AppTitle(
            text = stringResource(id = uiR.string.core_ui_categories),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                if (category == null) return@forEach
                CategoryChipOutlined(
                    text = stringResource(category.category.textId),
                    isSelected = category.isSelected,
                    onClick = { onSelectedCategory(category) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCategoriesSectionPreview() {
    AddCategoriesSection(
        categories = RecipeCategory.entries.toList()
            .map { category ->
                if (category != RecipeCategory.ALL) CategoryForChoose(category, false)
                else null
            },
        onSelectedCategory = {},
        modifier = Modifier.padding(16.dp)
    )
}