package recipia.feature.main_screen.impl

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import recipia.feature.add_recipe.impl.addRecipeScreen
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.recipeListScreen

@Composable
fun MainScreen() {
    val childNavController = rememberNavController()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index, screen ->
                    selectedIndex = index
                    childNavController.navigate(screen) {
                        popUpTo(childNavController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = childNavController,
            startDestination = RecipeListRoutingContract.RecipeList,
            modifier = Modifier.padding(contentPadding)
        ) {
            recipeListScreen(childNavController)
            addRecipeScreen(childNavController)
        }
    }
}