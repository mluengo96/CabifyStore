package com.mluengo.cabifystore.data.source.network

import com.mluengo.cabifystore.data.model.ProductModel
import com.squareup.moshi.Json

data class ProductApiModel(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "price") val price: Double
)

fun ProductApiModel.asModel() = ProductModel(
    code = code,
    name = name,
    price = price
)