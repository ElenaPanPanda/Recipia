package com.example.recipia.feature.collections.impl.ui.collectiondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipia.core.common.string_res_provider.StringResProvider
import com.example.recipia.core.ui.R as CoreR
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
    private val stringProvider: StringResProvider,
    savedStateHandle: SavedStateHandle,
    private val getCollectionByIdUseCase: GetCollectionByIdUseCase
) : ViewModel() {
    private val collectionId: String = savedStateHandle["collectionId"]
        ?: throw IllegalStateException("collectionId is null")

    private val _uiState = MutableStateFlow<CollectionDetailsState>(CollectionDetailsState.Loading)
    val uiState: StateFlow<CollectionDetailsState> = _uiState.asStateFlow()

    fun obtainEvent(event: CollectionDetailsEvent) {
    }

    init {
        loadCollection(collectionId)
    }

    private fun loadCollection(id: String) = viewModelScope.launch {
        try {
            val collection = getCollectionByIdUseCase.get(id)
            _uiState.update { CollectionDetailsState.Success(collection = collection) }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                CollectionDetailsState.Error(
                    message = stringProvider.getString(CoreR.string.core_ui_collections_not_found_error)
                )
            }
        }
    }
}