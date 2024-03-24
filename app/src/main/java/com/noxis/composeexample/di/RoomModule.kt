package com.noxis.composeexample.di

import android.content.Context
import androidx.room.Room
import com.noxis.lib_product.dao.ProductDao
import com.noxis.lib_product.db.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.productDao()
    }

}