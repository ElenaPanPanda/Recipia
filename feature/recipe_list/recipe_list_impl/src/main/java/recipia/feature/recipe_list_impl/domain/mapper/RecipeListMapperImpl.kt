package recipia.feature.recipe_list_impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.recipe_list_impl.data.dto.RecipeListResponse
import javax.inject.Inject

internal class RecipeListMapperImpl @Inject constructor() : RecipeListMapper {
    override fun convert(response: RecipeListResponse): List<ShortRecipe> {
        with(response) {
            return recipes.map { recipe ->
                ShortRecipe(
                    id = recipe.id,
                    title = recipe.title,
                    isFavorite = recipe.isFavorite,
                    imageUrl = recipe.imageUrl,
                    rawCategories = recipe.rawCategories,
                )
            }
        }
    }
}