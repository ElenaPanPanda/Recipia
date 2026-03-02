package com.example.recipia.feature.cookingmode.impl.ui

sealed interface CookingModeEvent {
    data object OnBackPressed : CookingModeEvent
}
