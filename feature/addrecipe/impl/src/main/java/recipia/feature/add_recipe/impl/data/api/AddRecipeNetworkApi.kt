package recipia.feature.add_recipe.impl.data.api

import com.example.recipia.core.common.model.FullRecipe
import recipia.feature.add_recipe.impl.data.dto.GetRecipeResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AddRecipeNetworkApi {
    @POST("recipes")
    suspend fun addRecipe(@Body body: FullRecipe): GetRecipeResponse
}