package com.nicolascristaldo.shoppinglist.ui.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class CategoriesModel(
    val name: String,
    @DrawableRes val image: Int,
    @ColorRes val color: Int
)
