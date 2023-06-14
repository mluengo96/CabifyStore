package com.mluengo.cabifystore.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mluengo.cabifystore.data.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * from products WHERE code = :productCode")
    fun getProductEntity(productCode: String): Flow<ProductEntity>

    @Query("SELECT * from products")
    fun getCartEntity(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductEntity(product: ProductEntity)

    @Delete
    suspend fun deleteEntity(product: ProductEntity)
}