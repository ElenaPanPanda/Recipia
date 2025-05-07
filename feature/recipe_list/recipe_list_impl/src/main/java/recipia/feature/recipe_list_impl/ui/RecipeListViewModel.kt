package recipia.feature.recipe_list_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.common.string_res_provider.StringResProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import recipia.feature.recipe_list_impl.domain.usecase.GetRecipesUseCase
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    private val getRecipesUseCase: GetRecipesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeListState())
    val uiState: StateFlow<RecipeListState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeListEffect>()
    val uiEffect: SharedFlow<RecipeListEffect> = _uiEffect.asSharedFlow()

    private val fakeRecipeList = listOf(
        ShortRecipe(
            id = "1",
            title = "Spaghetti",
            isFavorite = true,
            imageUrl = "https://naukajedzenia.pl/wp-content/uploads/2022/01/spaghetti-a-la-bolognese.5.jpg",
        ),
        ShortRecipe(
            id = "2",
            title = "Pizza",
            isFavorite = false,
            imageUrl = "https://www.thursdaynightpizza.com/wp-content/uploads/2019/02/pepperoni-broc-rabe-honey_featured-image.png"
        ),
        ShortRecipe(
            id = "3",
            title = "Sushi",
            isFavorite = true,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBEYblMFA6Vb7MitrfwuAK6R0PIQ1scNNY-Q&s"
        ),
        ShortRecipe(
            id = "4",
            title = "Salad",
            isFavorite = false,
            imageUrl = "https://www.allrecipes.com/thmb/Q84xeMgnOJPZAUOdNyrb9dbFZr4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/285625-ChefsSalad_MFS_006-2550ecee70ae46dbaec530a58314c99c.jpg"
        ),
        ShortRecipe(
            id = "5",
            title = "Steak",
            isFavorite = true,
            imageUrl = "https://food-images.files.bbci.co.uk/food/recipes/rib-eye_steak_with_61963_16x9.jpg"
        ),
        ShortRecipe(
            id = "6",
            title = "Burger",
            isFavorite = false,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLvBSYpdrmjC6s63P5oWgTiU4gPl36dkdVfQ&s"
        ),
        ShortRecipe(
            id = "7",
            title = "Pasta",
            isFavorite = true,
            imageUrl = "https://images.themodernproper.com/billowy-turkey/production/posts/PastaPrimavera_10.jpg?w=1200&h=1200&q=60&fm=jpg&fit=crop&dm=1719193287&s=0104e0b241aea73e5709db128503d749"
        ),
        ShortRecipe(
            id = "8",
            title = "Salad very long long long loooooong looooooon loooooooong loooong long name",
            isFavorite = false,
            imageUrl = "https://www.thespruceeats.com/thmb/2GoDt7juuaU7lxtQDq-_IjEr6t8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/SES-our-favorite-simple-green-salad-recipe-7370448-hero-01-4ca9788a0a3c4d53b70f1d07f8382b7f.jpg"
        ),
        ShortRecipe(
            id = "9",
            title = "Very long long long long name Pizza",
            isFavorite = true,
            imageUrl = "https://recipesblob.oetker.in/assets/d8a4b00c292a43adbb9f96798e028f01/1272x764/pizza-pollo-arrostojpg.webp"
        ),
    )

    fun obtainEvent(event: RecipeListEvent) {
        when (event) {
            is RecipeListEvent.OnLikeClicked -> onLikeClicked(event.recipeId)
        }
    }

    init {
        getFakeRecipes()
        //getRecipes()
    }

    private fun getFakeRecipes() {
        _uiState.update { it.copy(recipes = fakeRecipeList) }
    }

    private fun getRecipes() = viewModelScope.launch {
        getRecipesUseCase.getRecipes()
            .onSuccess { recipes ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = recipes,
                    )
                }
            }
            .onFailure {
                _uiEffect.emit(RecipeListEffect.ShowSnackBar("Something went wrong")) // TODO: Move to res
            }
    }

    private fun onLikeClicked(recipeId: String) {}
}


























