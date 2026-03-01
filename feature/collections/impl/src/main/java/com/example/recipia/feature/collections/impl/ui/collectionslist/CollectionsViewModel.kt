package com.example.recipia.feature.collections.impl.ui.collectionslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    private val getCollectionsListUseCase: GetCollectionsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CollectionsState>(CollectionsState.Loading)
    val uiState: StateFlow<CollectionsState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CollectionsEffect>()
    val uiEffect: SharedFlow<CollectionsEffect> = _uiEffect.asSharedFlow()

    fun obtainEvent(event: CollectionsEvent) {
        when (event) {
            is CollectionsEvent.OnCollectionClicked -> navigateToCollection(event.collectionId)
        }
    }

    init {
        loadCollections()
    }

    private fun loadCollections() = viewModelScope.launch {
        /*try {
            val collections = getCollectionsListUseCase.get()
            _uiState.update { CollectionsState.Success(collections = collections) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                CollectionsState.Error(
                    message = stringProvider.getString(CoreR.string.core_ui_collections_not_found_error)
                )
            }
        }*/

        // TODO: remove this
        _uiState.update { CollectionsState.Success(collections = COLLECTIONS) }
    }

    private fun navigateToCollection(collectionId: String) = viewModelScope.launch {
        _uiEffect.emit(CollectionsEffect.NavigateToCollection(collectionId))
    }

    companion object {
        val COLLECTIONS = listOf(
            UserCollection(
                collectionId = "1",
                collectionName = "Favorites",
                recipes = emptyList()
            ),
            UserCollection(
                collectionId = "2",
                collectionName = "Breakfast",
                recipes = emptyList()
            ),
            UserCollection(
                collectionId = "3",
                collectionName = "Lunch",
                recipes = emptyList()
            ),
        )
    }
}