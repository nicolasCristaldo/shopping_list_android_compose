package com.nicolascristaldo.shoppinglist.ui.screens.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.shoppinglist.data.repositories.ProductsRepository
import com.nicolascristaldo.shoppinglist.ui.model.CategoriesModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ListViewModel(productsRepository: ProductsRepository) : ViewModel() {

    val listUiState: StateFlow<ListUiState> =
        productsRepository.getAllProductsStream().map { ListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                initialValue = ListUiState(),
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS)
            )

    var selectedCategories = mutableStateListOf<CategoriesModel>()


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}