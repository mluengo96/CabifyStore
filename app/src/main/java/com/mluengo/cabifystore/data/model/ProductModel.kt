package com.mluengo.cabifystore.data.model

import com.mluengo.cabifystore.data.database.model.ProductEntity

data class ProductModel(
    val code: String,
    val name: String,
    val price: Double,
    var quantity: Int = 1,
    var discountedPrice: Double = -1.0,
)

fun ProductModel.asEntity() = ProductEntity(
    code = code,
    name = name,
    price = price,
    quantity = quantity,
    discountedPrice = discountedPrice,
)