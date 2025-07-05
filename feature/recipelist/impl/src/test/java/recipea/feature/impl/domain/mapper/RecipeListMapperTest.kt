package recipea.feature.impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.ui.model.PlaceholderColor
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import recipia.feature.impl.data.dto.RecipeListResponse
import recipia.feature.impl.data.dto.ShortRecipeDto
import recipia.feature.impl.domain.mapper.RecipeListMapperImpl

class RecipeListMapperTest {
    private val mapper = RecipeListMapperImpl()

    @Test
    fun `mapper converts response correctly`() {
        // given
        val response = RecipeListResponse(
            recipes = listOf(
                ShortRecipeDto(
                    id = "id",
                    title = "title",
                    rating = 3,
                    imageUrl = "url",
                    placeholderColor = PlaceholderColor.DARK_RED,
                    rawCategories = emptyList(),
                ),
                ShortRecipeDto(
                    id = "id1",
                    title = "title1",
                    rating = 3,
                    imageUrl = "url1",
                    placeholderColor = PlaceholderColor.DARK_TEAL,
                    rawCategories = emptyList(),
                )
            )
        )

        // expected result
        val expected = listOf(
            ShortRecipe(
                id = "id",
                title = "title",
                rating = 3,
                imageUrl = "url",
                placeholderColor = PlaceholderColor.DARK_RED,
            ),
            ShortRecipe(
                id = "id1",
                title = "title1",
                rating = 3,
                imageUrl = "url1",
                placeholderColor = PlaceholderColor.DARK_TEAL,
            )
        )

        val result = mapper.convert(response)

        assertThat(result).isEqualTo(expected)
    }
}