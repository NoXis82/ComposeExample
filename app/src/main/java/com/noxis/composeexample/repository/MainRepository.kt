package com.noxis.composeexample.repository

import com.noxis.lib_product.dao.ProductDao
import com.noxis.lib_product.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository (private val productDao: ProductDao) {

    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    suspend fun addProduct(product: Product) {
        productDao.insert(product)
    }

    suspend fun clearAll() {
        productDao.clearAll()
    }

}