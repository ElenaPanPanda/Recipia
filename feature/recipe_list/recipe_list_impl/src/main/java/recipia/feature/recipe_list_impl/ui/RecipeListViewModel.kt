package recipia.feature.recipe_list_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R as uiR
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
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeListState())
    val uiState: StateFlow<RecipeListState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeListEffect>()
    val uiEffect: SharedFlow<RecipeListEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: RecipeListEvent) {
        when (event) {
            is RecipeListEvent.OnCategorySelected -> onSelectedCategory(event.category)
        }
    }

    init {
        getRecipes()
    }

    private fun getRecipes() = viewModelScope.launch {
        try {
            val recipes = getRecipesUseCase.getRecipes()
            _uiState.update {
                it.copy(
                    isLoading = true,
                    recipes = recipes,
                    filteredRecipes = recipes
                )
            }
        } catch (e: Exception) {
            _uiEffect.emit(
                RecipeListEffect.ShowSnackBar(
                    stringProvider.getString(uiR.string.core_ui_common_error)
                )
            )
        }
    }

    private fun onSelectedCategory(category: RecipeCategory) {
        _uiState.update { it.copy(selectedCategory = category) }

        when (category) {
            RecipeCategory.ALL -> _uiState.update { it.copy(filteredRecipes = uiState.value.recipes) }
            else -> {
                val filteredRecipes = uiState.value.recipes.filter { recipe ->
                    recipe.rawCategories.contains(category)
                }
                _uiState.update { it.copy(filteredRecipes = filteredRecipes) }
            }
        }
    }
}