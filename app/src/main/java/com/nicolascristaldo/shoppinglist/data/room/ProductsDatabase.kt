package com.nicolascristaldo.shoppinglist.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolascristaldo.shoppinglist.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDao

    companion object {
        @Volatile
        private var Instance: ProductsDatabase? = null

        fun getDatabase(ctx: Context): ProductsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(ctx, ProductsDatabase::class.java, "products_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}