package com.nicolascristaldo.shoppinglist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Name")
    val name: String,
    @ColumnInfo("Price")
    val price: Double,
    @ColumnInfo("Category")
    val category: String,
)
