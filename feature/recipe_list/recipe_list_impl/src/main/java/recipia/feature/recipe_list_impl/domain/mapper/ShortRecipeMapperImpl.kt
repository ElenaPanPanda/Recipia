package recipia.feature.recipe_list_impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.recipe_list_impl.data.dto.ShortRecipeDto
import javax.inject.Inject

internal class ShortRecipeMapperImpl @Inject constructor() : ShortRecipeMapper {
    override fun convert(request: ShortRecipe): ShortRecipeDto {
        with(request) {
            return ShortRecipeDto(
                id = id,
                title = title,
                isFavorite = isFavorite,
                imageUrl = imageUrl,
                rawCategories = rawCategories,
            )
        }
    }
}