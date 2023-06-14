package com.mluengo.cabifystore.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mluengo.cabifystore.data.model.ProductModel

@Entity(
    tableName = "products"
)
data class ProductEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val discountedPrice: Double,
)

fun ProductEntity.asExternalModel() = ProductModel(
    code = code,
    name = name,
    price = price,
    quantity = quantity,
    discountedPrice = discountedPrice,
)