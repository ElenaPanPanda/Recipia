package com.example.recipia.feature.collections.impl.ui.collectiondetails

import com.example.recipia.core.common.model.UserCollection

sealed interface CollectionDetailsState {
    data object Loading : CollectionDetailsState
    data class Error(val message: String) : CollectionDetailsState
    data class Success(val collection: UserCollection) : CollectionDetailsState
}