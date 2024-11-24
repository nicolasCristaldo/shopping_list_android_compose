package com.nicolascristaldo.shoppinglist.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.shoppinglist.data.repositories.ProductsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ListViewModel(productsRepository: ProductsRepository): ViewModel() {
    val listUiState: StateFlow<ListUiState> =
        productsRepository.getAllProductsStream().map { ListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                initialValue = ListUiState(),
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS)
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}