package com.nicolascristaldo.shoppinglist.data.repositories

import com.nicolascristaldo.shoppinglist.data.room.ProductsDao
import com.nicolascristaldo.shoppinglist.model.Product
import kotlinx.coroutines.flow.Flow

class OfflineProductsRepository(private val productsDao: ProductsDao): ProductsRepository {

    override fun getAllProductsStream(): Flow<List<Product>> = productsDao.getAllProducts()

    override fun getProductStream(id: Int): Flow<Product> = productsDao.getItem(id)

    override suspend fun insert(product: Product) = productsDao.insert(product)

    override suspend fun update(product: Product) = productsDao.update(product)

    override suspend fun delete(product: Product) = productsDao.delete(product)
}