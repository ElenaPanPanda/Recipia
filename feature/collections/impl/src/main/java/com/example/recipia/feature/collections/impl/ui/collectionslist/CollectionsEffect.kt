package com.example.recipia.feature.collections.impl.ui.collectionslist

sealed class CollectionsEffect {
    data class NavigateToCollection(val collectionId: String) : CollectionsEffect()
}