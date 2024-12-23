package com.nicolascristaldo.shoppinglist.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nicolascristaldo.shoppinglist.ShoppingListApplication
import com.nicolascristaldo.shoppinglist.ui.screens.list.ListViewModel
import com.nicolascristaldo.shoppinglist.ui.screens.product.add.AddProductViewModel
import com.nicolascristaldo.shoppinglist.ui.screens.product.details.ProductDetailsViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {

        initializer {
            ListViewModel(shoppingListApplication().container.productsRepository)
        }

        initializer {
            AddProductViewModel(shoppingListApplication().container.productsRepository)
        }

        initializer {
            ProductDetailsViewModel(
                this.createSavedStateHandle(),
                shoppingListApplication().container.productsRepository
            )
        }
    }
}

fun CreationExtras.shoppingListApplication(): ShoppingListApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShoppingListApplication)