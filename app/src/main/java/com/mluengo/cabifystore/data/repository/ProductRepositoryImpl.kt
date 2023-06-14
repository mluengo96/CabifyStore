package com.mluengo.cabifystore.data.repository

import com.mluengo.cabifystore.data.database.dao.ProductDao
import com.mluengo.cabifystore.data.database.model.ProductEntity
import com.mluengo.cabifystore.data.database.model.asExternalModel
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.data.model.asEntity
import com.mluengo.cabifystore.data.source.network.ProductApiModel
import com.mluengo.cabifystore.data.source.network.RetrofitNetworkApi
import com.mluengo.cabifystore.data.source.network.asModel
import com.mluengo.cabifystore.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val productDao: ProductDao,
): ProductRepository {
    override suspend fun getProducts(): Flow<List<ProductModel>> =
        flow {
            networkApi
                .getProducts()
                .body()?.let {
                    emit(
                        it.products.map(ProductApiModel::asModel)
                    )
                }
        }.flowOn(Dispatchers.IO)

    override fun getProduct(productCode: String): Flow<ProductModel> =
        productDao.getProductEntity(productCode = productCode).map { it.asExternalModel() }

    override fun getCart(): Flow<List<ProductModel>> =
        productDao.getCartEntity()
            .map { it.map(ProductEntity::asExternalModel) }

    override suspend fun insertProduct(product: ProductModel) =
        productDao.insertProductEntity(product = product.asEntity())


    override suspend fun delete(product: ProductModel) =
        productDao.deleteEntity(product = product.asEntity())
}