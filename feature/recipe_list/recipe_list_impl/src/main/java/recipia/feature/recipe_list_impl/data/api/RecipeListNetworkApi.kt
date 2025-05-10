package recipia.feature.recipe_list_impl.data.api

import recipia.feature.recipe_list_impl.data.dto.RecipeListResponse
import retrofit2.http.GET

internal interface RecipeListNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListResponse
}