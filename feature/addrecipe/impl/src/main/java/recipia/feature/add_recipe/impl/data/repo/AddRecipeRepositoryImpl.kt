package recipia.feature.add_recipe.impl.data.repo

import com.example.recipia.core.common.model.FullRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import recipia.feature.add_recipe.impl.data.api.AddRecipeNetworkApi
import javax.inject.Inject

internal class AddRecipeRepositoryImpl @Inject constructor(private val api: AddRecipeNetworkApi) :
        AddRecipeRepository {
    override suspend fun addRecipe(recipe: FullRecipe) =
        withContext(Dispatchers.IO) {
            api.addRecipe(recipe)
        }
}