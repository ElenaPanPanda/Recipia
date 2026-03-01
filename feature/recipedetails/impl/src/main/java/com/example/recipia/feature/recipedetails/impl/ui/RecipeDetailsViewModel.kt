package com.example.recipia.feature.recipedetails.impl.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredient
import com.example.recipia.feature.recipedetails.impl.domain.model.DetailedIngredientSection
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.UpdateShoppingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    savedStateHandle: SavedStateHandle,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val checkAddedIngredientsInShoppingListUseCase: CheckAddedIngredientsInShoppingListUseCase,
    private val addAllIngredientsToShoppingListUseCase: AddAllIngredientsToShoppingListUseCase,
    private val updateShoppingListUseCase: UpdateShoppingListUseCase,
) : ViewModel() {
    private val recipeId: String = savedStateHandle["recipeId"]
        ?: throw IllegalStateException("recipeId is null")

    private val _uiState = MutableStateFlow<RecipeDetailsState>(RecipeDetailsState.Loading)
    val uiState: StateFlow<RecipeDetailsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RecipeDetailsEffect>()
    val uiEffect: SharedFlow<RecipeDetailsEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.OnEditClicked -> onEditClick(event.recipeId)
            is RecipeDetailsEvent.OnSaveClicked -> onSaveClick(event.recipeId)
            is RecipeDetailsEvent.OnCalendarClicked -> onCalendarClick(event.recipeId)
            is RecipeDetailsEvent.OnShareClicked -> onShareClick(event.recipeId)
            is RecipeDetailsEvent.OnDeleteClicked -> onDeleteClick(event.recipeId)
            is RecipeDetailsEvent.OnAddAllIngredientsClicked -> addAllIngredientsToShoppingList(
                event.recipeName,
                event.ingredients
            )

            is RecipeDetailsEvent.OnAddIngredientClicked -> addIngredientToShoppingList(
                event.recipeName,
                event.ingredient
            )
        }
    }

    init {
        viewModelScope.launch {
            loadRecipe(recipeId)
            updateCheckedIngredients()
        }
    }

    private suspend fun loadRecipe(recipeId: String) {
        try {
            val recipe = getRecipeUseCase.getRecipe(recipeId)
            _uiState.update { RecipeDetailsState.Success(recipe = recipe) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                RecipeDetailsState.Error(
                    message = stringProvider.getString(R.string.core_ui_recipe_not_found_error)
                )
            }
        }
    }

    private suspend fun updateCheckedIngredients() {
        val currentState = _uiState.value as? RecipeDetailsState.Success ?: return

        checkAddedIngredientsInShoppingListUseCase
            .getAddedIngredients(currentState.recipe.title)
            .collect { checkedIngredients ->
                val allIngredientsAmount = currentState.recipe.ingredients
                    .flatMap { it.ingredientsList }
                    .size

                val addedNames = checkedIngredients.map { it.ingredient }.toSet()

                val updatedIngredients = currentState.recipe.ingredients.map { section ->
                    section.copy(
                        ingredientsList = section.ingredientsList.map { ingredient ->
                            ingredient.copy(
                                addedToList = addedNames.contains(ingredient.ingredient)
                            )
                        }
                    )
                }

                val updatedRecipe = currentState.recipe.copy(ingredients = updatedIngredients)

                _uiState.update {
                    currentState.copy(
                        recipe = updatedRecipe,
                        isAllIngredientsChecked = checkedIngredients.size == allIngredientsAmount
                    )
                }
            }
    }


    private fun onEditClick(recipeId: String) {}

    private fun onSaveClick(recipeId: String) {
        _uiState.update { (it as RecipeDetailsState.Success).copy(recipeIdToSave = recipeId) }

        getCollections()

        val currentState = _uiState.value as? RecipeDetailsState.Success ?: return
        Timber.tag("RecipeDetailsViewModel").d("onSaveClick: currentState = $currentState")
        if (currentState.collections != null) {
            openCollectionsBottomSheet()
        }
    }

    private fun getCollections() = viewModelScope.launch {
        try {
            val collections = getCollectionsUseCase.getCollections()

            _uiState.update { (it as RecipeDetailsState.Success).copy(collections = collections) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                RecipeDetailsState.Error(
                    message = stringProvider.getString(R.string.core_ui_collections_not_found_error)
                )
            }
        }
    }

    private fun openCollectionsBottomSheet() = viewModelScope.launch {
        _uiEffect.emit(RecipeDetailsEffect.OpenCollectionsBottomSheet)
    }

    private fun changeNewCollectionValue(value: String) {
        _uiState.update {
            (it as RecipeDetailsState.Success).copy(
                newCollectionValue = value,
            )
        }
        updateStateOfSaveToCollectionButton()
    }

    private fun updateStateOfSaveToCollectionButton() {
        val currentState = _uiState.value as? RecipeDetailsState.Success ?: return

        _uiState.update {
            currentState.copy(
                saveRecipeInCollectionButtonIsEnabled = currentState.collections?.any { it.isSelected } == true ||
                        currentState.newCollectionValue.isNotEmpty()
            )
        }
    }

    private fun saveRecipeToCollection() = viewModelScope.launch {
        val currentState = _uiState.value as? RecipeDetailsState.Success ?: return@launch
        val recipeId = currentState.recipeIdToSave ?: return@launch

        val createNewCollection = currentState.newCollectionValue.isNotEmpty()
        if (createNewCollection) {
            createCollectionUseCase.create(
                collectionName = currentState.newCollectionValue,
                recipeId = recipeId
            )
        }

        val selectedCollections = currentState.collections?.filter { it.isSelected } ?: emptyList()
        selectedCollections.forEach {
            addRecipeToCollectionUseCase.add(it.collectionId, recipeId)
        }
    }

    private fun changeCollectionSelection(collectionId: String, isSelected: Boolean) {
        _uiState.update {
            (it as RecipeDetailsState.Success).copy(
                collections = it.collections?.map { collection ->
                    if (collection.collectionId == collectionId) {
                        collection.copy(isSelected = isSelected)
                    } else {
                        Timber.tag("While changing collection selection")
                            .e("Collection with id $collectionId was not found")
                        collection
                    }
                },
            )
        }
        updateStateOfSaveToCollectionButton()
    }

    private fun onCalendarClick(recipeId: String) {}

    private fun onShareClick(recipeId: String) {}

    private fun onDeleteClick(recipeId: String) {}

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun addAllIngredientsToShoppingList(
        recipeName: String,
        ingredients: List<DetailedIngredientSection>
    ) = viewModelScope.launch {
        addAllIngredientsToShoppingListUseCase.add(
            recipeName = recipeName,
            ingredients = ingredients
        )
    }

    private fun addIngredientToShoppingList(
        recipeName: String,
        ingredient: DetailedIngredient
    ) = viewModelScope.launch {
        // TODO: add or update if recipe is already in shopping list
    }
}