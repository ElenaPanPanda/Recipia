package recipia.feature.add_recipe.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.add_recipe.impl.ui.AddRecipeScreen

fun NavGraphBuilder.addRecipeScreen() {
    composable<AddRecipeRoutingContract.AddRecipe> {
        AddRecipeScreen()
    }
}