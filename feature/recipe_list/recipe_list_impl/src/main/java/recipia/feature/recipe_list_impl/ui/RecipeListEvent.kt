package recipia.feature.recipe_list_impl.ui

import com.example.recipia.core.common.model.RecipeCategory

sealed interface RecipeListEvent {
    data class OnCategorySelected(val category: RecipeCategory) : RecipeListEvent
}