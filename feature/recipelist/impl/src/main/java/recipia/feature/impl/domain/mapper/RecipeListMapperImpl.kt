package recipia.feature.impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.impl.data.dto.RecipeListResponse
import javax.inject.Inject

internal class RecipeListMapperImpl @Inject constructor() : RecipeListMapper {
    override fun convert(response: RecipeListResponse): List<ShortRecipe> {
        with(response) {
            return recipes.map { recipe ->
                ShortRecipe(
                    id = recipe.id,
                    title = recipe.title,
                    rating = recipe.rating,
                    imageUrl = recipe.imageUrl,
                    placeholderColor = recipe.placeholderColor,
                    rawCategories = recipe.rawCategories,
                )
            }
        }
    }
}