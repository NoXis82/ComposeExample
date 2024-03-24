package com.noxis.lib_product.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noxis.lib_product.dao.ProductDao
import com.noxis.lib_product.model.Product


@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}