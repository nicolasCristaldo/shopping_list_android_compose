package com.nicolascristaldo.shoppinglist.data

import android.content.Context
import com.nicolascristaldo.shoppinglist.data.repositories.OfflineProductsRepository
import com.nicolascristaldo.shoppinglist.data.repositories.ProductsRepository
import com.nicolascristaldo.shoppinglist.data.room.ProductsDatabase

interface AppContainer {
    val productsRepository: ProductsRepository
}

class AppDataContainer(private val ctx: Context): AppContainer {
    override val productsRepository: ProductsRepository by lazy {
        OfflineProductsRepository(ProductsDatabase.getDatabase(ctx).productsDao())
    }
}