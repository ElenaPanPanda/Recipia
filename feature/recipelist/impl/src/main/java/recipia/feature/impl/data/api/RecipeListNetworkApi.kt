package recipia.feature.impl.data.api

import recipia.feature.impl.data.dto.RecipeListResponse
import retrofit2.http.GET

internal interface RecipeListNetworkApi {
    @GET("recipes/short")
    suspend fun getRecipes(): RecipeListResponse
}