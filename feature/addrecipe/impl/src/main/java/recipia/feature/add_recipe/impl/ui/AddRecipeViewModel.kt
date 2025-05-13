package recipia.feature.add_recipe.impl.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddRecipeState())
    val uiState: StateFlow<AddRecipeState> = _uiState.asStateFlow()

    fun obtainEvent(event: AddRecipeEvent) {
        when (event) {
            is AddRecipeEvent.OnTitleInputChanged -> changeTitleInput(event.value)
            is AddRecipeEvent.OnInstructionsInputChanged -> changeInstructionsInput(event.value)
            is AddRecipeEvent.OnSaveClicked -> saveRecipe()
        }
    }

    private fun changeTitleInput(value: String) {
        _uiState.update { it.copy(titleInput = value) }
        // TODO: validate?
        if (value.isNotEmpty()) {
            _uiState.update { it.copy(enabledSaveButton = true) }
        } else {
            _uiState.update { it.copy(enabledSaveButton = false) }
        }
    }

    private fun changeInstructionsInput(value: String) {
        _uiState.update { it.copy(instructionsInput = value) }
    }

    private fun saveRecipe() {}
}