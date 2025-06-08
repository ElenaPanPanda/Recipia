package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val shoppingListFlow: Flow<List<ShoppingListItemDatastoreModel>> =
        context.shoppingListDataStore.data.map { proto ->
            proto.itemsList.map { it.toDatastoreModel() }
        }

    suspend fun addItem(item: ShoppingListItemDatastoreModel) {
        context.shoppingListDataStore.updateData { current ->
            current.toBuilder()
                .addItems(item.toProto())
                .build()
        }
    }

    suspend fun addIngredientToItem(index: Int, item: ShoppingListIngredientDatastoreModel) {
        val currentList = shoppingListFlow.first()

        val updatedItem = currentList[index].copy(
            ingredientsList = listOf(item) + currentList[index].ingredientsList
        )

        context.shoppingListDataStore.updateData { current ->
            val builder = current.toBuilder()
            builder.setItems(index, updatedItem.toProto())
            builder.build()
        }
    }

    suspend fun removeItem(index: Int) {
        context.shoppingListDataStore.updateData { current ->
            val builder = current.toBuilder()
            builder.removeItems(index)
            builder.build()
        }
    }
}

val Context.shoppingListDataStore: DataStore<ShoppingList> by dataStore(
    fileName = "shopping_list.pb",
    serializer = ShoppingListSerializer
)