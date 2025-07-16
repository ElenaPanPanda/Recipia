package recipia.feature.impl.ui

import androidx.compose.runtime.Stable
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.model.ShortRecipe

@Stable
data class RecipeListState(
    val isLoading: Boolean = true,
    val recipes: List<ShortRecipe> = emptyList(),
    val filteredRecipes: List<ShortRecipe> = emptyList(),
    val categories: List<RecipeCategory> = RecipeCategory.entries.toList(),
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
)