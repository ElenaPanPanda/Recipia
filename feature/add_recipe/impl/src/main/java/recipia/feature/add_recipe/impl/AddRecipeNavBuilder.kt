package recipia.feature.add_recipe.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.add_recipe.impl.ui.AddRecipeScreen

fun NavGraphBuilder.addRecipeScreen(navController: NavHostController) {
    composable<AddRecipeRoutingContract.AddRecipe> {
        AddRecipeScreen()
    }
}