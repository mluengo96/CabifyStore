package com.mluengo.cabifystore.data.source.network

import com.mluengo.cabifystore.data.model.ProductsModel
import com.squareup.moshi.Json

data class ProductsApiModel(
    @field:Json(name = "products") val products: List<ProductApiModel>,
)

fun ProductsApiModel.asModel() = ProductsModel(
    products = listOf()
)