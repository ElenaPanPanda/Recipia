package recipia.feature.recipe_list_impl.data.dto

import com.example.recipia.core.common.model.RecipeCategory
import kotlinx.serialization.Serializable

@Serializable
data class RecipeListResponse(val recipes: List<ShortRecipeResponse>) {
    @Serializable
    data class ShortRecipeResponse(
        val id: String,
        val title: String,
        val isFavorite: Boolean,
        val imageUrl: String,
        val rawCategories: List<RecipeCategory>,
    )
}