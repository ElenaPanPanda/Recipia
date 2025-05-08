package recipia.feature.recipe_list_impl.ui

import androidx.compose.runtime.Stable
import com.example.recipia.core.common.model.ShortRecipe

@Stable
data class RecipeListState(
    val isLoading: Boolean = false,

    /**
     * Block of states for recipe list
     * */
    val recipes: List<ShortRecipe> = emptyList(),
)