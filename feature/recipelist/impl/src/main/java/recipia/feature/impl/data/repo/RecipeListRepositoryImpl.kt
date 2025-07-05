package recipia.feature.impl.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import recipia.feature.impl.data.api.RecipeListNetworkApi
import javax.inject.Inject

internal class RecipeListRepositoryImpl @Inject constructor(private val api: RecipeListNetworkApi) :
    RecipeListRepository {
    override suspend fun getRecipes() =
        withContext(Dispatchers.IO) {
            api.getRecipes()
        }
}