package com.noxis.lib_product.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noxis.lib_product.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("DELETE FROM products")
    suspend fun clearAll()

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

}