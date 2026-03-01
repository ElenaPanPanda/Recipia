package com.example.recipia.feature.collections.impl.ui.collectionslist

import com.example.recipia.core.common.model.UserCollection

sealed interface CollectionsState {
    data object Loading : CollectionsState
    data class Error(val message: String) : CollectionsState
    data class Success(val collections: List<UserCollection>) : CollectionsState
}