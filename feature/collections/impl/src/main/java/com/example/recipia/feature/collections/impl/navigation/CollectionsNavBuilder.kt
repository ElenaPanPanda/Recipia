package com.example.recipia.feature.collections.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.recipia.feature.collections.api.CollectionsRoutingContract
import com.example.recipia.feature.collections.impl.ui.collectiondetails.CollectionDetailsScreen
import com.example.recipia.feature.collections.impl.ui.collectionslist.CollectionsScreen

fun NavGraphBuilder.collectionsScreen(navController: NavController) {
    composable<CollectionsRoutingContract.Collections> {
        CollectionsScreen(navigateToCollection = { collectionId ->
            navController.navigate(
                CollectionsRoutingContract.CollectionDetails(
                    collectionId = collectionId
                )
            )
        })
    }

    composable<CollectionsRoutingContract.CollectionDetails> {
        val route = it.toRoute<CollectionsRoutingContract.CollectionDetails>()
        CollectionDetailsScreen(collectionId = route.collectionId)
    }
}