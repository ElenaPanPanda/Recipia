package recipia.feature.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.RecipeCategory

@Composable
fun CategoriesBar(
    categories: List<RecipeCategory>,
    selectedCategory: RecipeCategory,
    onCategorySelected: (RecipeCategory) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(categories) { category ->
                CategoryChipFilled(
                    text = stringResource(id = category.textId),
                    isSelected = category == selectedCategory,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesBarPreview() {
    CategoriesBar(
        categories = RecipeCategory.entries.toList(),
        selectedCategory = RecipeCategory.ALL,
        onCategorySelected = {},
    )
}