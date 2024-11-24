package com.nicolascristaldo.shoppinglist.ui.screens.product

import com.nicolascristaldo.shoppinglist.model.Product
import java.text.NumberFormat


data class ProductUiState(
    val productDetails: ProductDetails = ProductDetails(),
    val isEntryValid: Boolean = false
)

data class ProductDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val category: String = ""
)

fun ProductDetails.toProduct(): Product = Product(
    id = id,
    name = name,
    price = price.toDouble(),
    category = category
)

fun Product.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

fun Product.toProductUiState(isEntryValid: Boolean = false): ProductUiState = ProductUiState(
    productDetails = this.toProductDetails(),
    isEntryValid = isEntryValid
)

fun Product.toProductDetails(): ProductDetails = ProductDetails(
    id = id,
    name = name,
    price = price.toString(),
    category = category
)