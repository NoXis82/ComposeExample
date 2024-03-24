package com.noxis.composeexample.di

import com.noxis.composeexample.repository.MainRepository
import com.noxis.lib_product.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(productDao: ProductDao): MainRepository {
        return MainRepository(productDao)
    }
}