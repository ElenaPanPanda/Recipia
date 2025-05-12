package recipia.feature.recipe_list_impl.data.dto

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.Serializable

@Serializable
data class RecipeListResponse(val recipes: List<ShortRecipeDto>)

@Serializable
data class ShortRecipeDto(
    val id: String,
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val placeholderColor: PlaceholderColor,
    val rawCategories: List<RecipeCategory>,
)