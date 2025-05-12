package com.example.recipia.feature.collections.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.collections.api.CollectionsRoutingContract
import com.example.recipia.feature.collections.impl.ui.CollectionsScreen

fun NavGraphBuilder.collectionsScreen() {
    composable<CollectionsRoutingContract.Collections> {
        CollectionsScreen()
    }
}