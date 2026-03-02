package com.example.recipia.feature.cookingmode.impl.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CookingModeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<CookingModeState>(CookingModeState.Loading)
    val uiState: StateFlow<CookingModeState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CookingModeEffect>()
    val uiEffect: SharedFlow<CookingModeEffect> = _uiEffect.asSharedFlow()

    init {
        _uiState.value = CookingModeState.Success()
    }

    fun obtainEvent(event: CookingModeEvent) {
        when (event) {
            is CookingModeEvent.OnBackPressed -> {
                // handle back press if needed
            }
        }
    }
}
