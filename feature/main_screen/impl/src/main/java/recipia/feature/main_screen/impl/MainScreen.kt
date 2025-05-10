package recipia.feature.main_screen.impl

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import recipia.feature.add_recipe.impl.addRecipeScreen
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.navigation.recipeListScreen

@Composable
fun MainScreen(
    navigateToRecipeDetails: (String) -> Unit
) {
    val childNavController = rememberNavController()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { contentPadding ->
        NavHost(
            navController = childNavController,
            startDestination = RecipeListRoutingContract.RecipeList,
            modifier = Modifier.padding(contentPadding)
        ) {
            recipeListScreen(
                onShowSnackBar = { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                },
                navigateToRecipeDetails = navigateToRecipeDetails,
                childNavController,
            )
            addRecipeScreen(childNavController)
        }
    }
}