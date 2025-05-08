package recipia.feature.recipe_list_impl.data.api

import recipia.feature.recipe_list_impl.data.dto.RecipeListResponse
import recipia.feature.recipe_list_impl.data.dto.ShortRecipeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

internal interface RecipeListNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListResponse

    @PATCH("recipes//short/{id}")
    suspend fun likeRecipe(
        @Path("id") recipeId: String,
        @Body recipe: ShortRecipeDto,
    )
}