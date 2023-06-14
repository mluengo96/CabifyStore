package com.mluengo.cabifystore.domain.repository

import com.mluengo.cabifystore.data.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<List<ProductModel>>

    fun getProduct(productCode: String): Flow<ProductModel>

    fun getCart(): Flow<List<ProductModel>>

    suspend fun insertProduct(product: ProductModel)

    suspend fun delete(product: ProductModel)
}