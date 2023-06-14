package com.mluengo.cabifystore.data.source.network

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitNetworkApi {
    @GET("Products.json/")
    suspend fun getProducts(): Response<ProductsApiModel>
}