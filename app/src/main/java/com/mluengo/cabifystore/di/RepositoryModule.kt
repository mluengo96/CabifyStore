package com.mluengo.cabifystore.di

import com.mluengo.cabifystore.data.repository.DiscountsRepositoryImpl
import com.mluengo.cabifystore.data.repository.ProductRepositoryImpl
import com.mluengo.cabifystore.domain.repository.DiscountsRepository
import com.mluengo.cabifystore.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindDiscountsRepository(
        discountsRepositoryImpl: DiscountsRepositoryImpl
    ): DiscountsRepository
}