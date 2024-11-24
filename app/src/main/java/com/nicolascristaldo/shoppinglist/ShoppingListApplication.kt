package com.nicolascristaldo.shoppinglist

import android.app.Application
import com.nicolascristaldo.shoppinglist.data.AppContainer
import com.nicolascristaldo.shoppinglist.data.AppDataContainer

class ShoppingListApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}