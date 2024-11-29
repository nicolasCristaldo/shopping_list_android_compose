package com.nicolascristaldo.shoppinglist.data.providers

import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.ui.model.CategoriesModel

class CategoriesProvider {
    fun getCategories() = listOf(
        CategoriesModel(
            name = "Others",
            image = R.drawable.ic_others,
            color = R.color.color_others
        ),
        CategoriesModel(
            name = "Accessories",
            image = R.drawable.ic_accessories,
            color = R.color.color_accessories
        ),
        CategoriesModel(
            name = "Electronics",
            image = R.drawable.ic_electronics,
            color = R.color.color_electronics
        ),
        CategoriesModel(
            name = "Clothing",
            image = R.drawable.ic_clothing,
            color = R.color.color_clothing
        ),
        CategoriesModel(
            name = "Groceries",
            image = R.drawable.ic_groceries,
            color = R.color.color_groceries
        ),
        CategoriesModel(
            name = "Furniture",
            image = R.drawable.ic_furniture,
            color = R.color.color_furniture
        ),
        CategoriesModel(
            name = "Beauty",
            image = R.drawable.ic_beauty,
            color = R.color.color_beauty
        ),
        CategoriesModel(
            name = "Health",
            image = R.drawable.ic_health,
            color = R.color.color_health
        ),
        CategoriesModel(
            name = "Tools",
            image = R.drawable.ic_tools,
            color = R.color.color_tools
        )
    )

    fun getCategory(name: String): CategoriesModel {
        return when (name) {
            "Accessories" -> getCategories()[1]
            "Electronics" -> getCategories()[2]
            "Clothing" -> getCategories()[3]
            "Groceries" -> getCategories()[4]
            "Furniture" -> getCategories()[5]
            "Beauty" -> getCategories()[6]
            "Health" -> getCategories()[7]
            "Tools" -> getCategories()[8]
            else -> getCategories()[0]
        }
    }
}