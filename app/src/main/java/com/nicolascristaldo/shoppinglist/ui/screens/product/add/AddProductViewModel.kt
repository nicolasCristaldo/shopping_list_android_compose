package com.nicolascristaldo.shoppinglist.ui.screens.product.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nicolascristaldo.shoppinglist.data.repositories.ProductsRepository
import com.nicolascristaldo.shoppinglist.ui.screens.product.ProductDetails
import com.nicolascristaldo.shoppinglist.ui.screens.product.ProductUiState
import com.nicolascristaldo.shoppinglist.ui.screens.product.toProduct

class AddProductViewModel(
    private val productsRepository: ProductsRepository
): ViewModel() {

    var productUiState by mutableStateOf(ProductUiState())
    private set

    fun updateUiState(productDetails: ProductDetails) {
        productUiState = ProductUiState(
            productDetails = productDetails,
            isEntryValid = validateInput(productDetails)
        )
    }

    private fun validateInput(uiState: ProductDetails = productUiState.productDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank()
        }
    }

    suspend fun saveProduct() {
        if(validateInput()) {
            productsRepository.insert(productUiState.productDetails.toProduct())
        }
    }
}