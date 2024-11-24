package com.nicolascristaldo.shoppinglist.ui.screens.list

import com.nicolascristaldo.shoppinglist.model.Product

data class ListUiState(
    val productList: List<Product> = listOf()
)
