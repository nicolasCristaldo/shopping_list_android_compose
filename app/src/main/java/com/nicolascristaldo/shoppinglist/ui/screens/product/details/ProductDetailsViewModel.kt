package com.nicolascristaldo.shoppinglist.ui.screens.product.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.shoppinglist.data.repositories.ProductsRepository
import com.nicolascristaldo.shoppinglist.ui.screens.product.ProductDetails
import com.nicolascristaldo.shoppinglist.ui.screens.product.ProductUiState
import com.nicolascristaldo.shoppinglist.ui.screens.product.toProduct
import com.nicolascristaldo.shoppinglist.ui.screens.product.toProductUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val productsRepository: ProductsRepository
): ViewModel() {

    var productUiState by mutableStateOf(ProductUiState())
        private set

    private val productId: Int = checkNotNull(savedStateHandle[ProductDetailsDestination.productId])

    init {
        viewModelScope.launch {
            productUiState = productsRepository.getProductStream(productId)
                .filterNotNull()
                .first()
                .toProductUiState(true)
        }
    }

    private fun validateInput(uiState: ProductDetails = productUiState.productDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank()
        }
    }

    suspend fun updateProduct() {
        if (validateInput(uiState = productUiState.productDetails)) {
            productsRepository.update(productUiState.productDetails.toProduct())
        }
    }

    suspend fun deleteProduct() {
        productsRepository.delete(productUiState.productDetails.toProduct())
    }

    fun updateUiState(productDetails: ProductDetails) {
        productUiState = ProductUiState(
            productDetails = productDetails,
            isEntryValid = validateInput(productDetails)
        )
    }
}