package com.nicolascristaldo.shoppinglist.data.repositories

import com.nicolascristaldo.shoppinglist.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getAllProductsStream(): Flow<List<Product>>

    fun getProductStream(id: Int): Flow<Product>

    suspend fun insert(product: Product)

    suspend fun update(product: Product)

    suspend fun delete(product: Product)
}