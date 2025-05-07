package recipia.feature.recipe_list_impl.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import recipia.feature.recipe_list_impl.ui.components.RecipeItem
import recipia.feature.recipe_list_impl.ui.RecipeListEffect.ShowSnackBar

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (RecipeListEvent) -> Unit = viewModel::obtainEvent
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            items(state.recipes) { recipe ->
                RecipeItem(
                    title = recipe.title,
                    imageUrl = recipe.imageUrl,
                    isFavorite = recipe.isFavorite,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}