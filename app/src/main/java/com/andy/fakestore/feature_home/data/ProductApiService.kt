package com.andy.fakestore.feature_home.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): ProductDto
    
    @GET("products/categories")
    suspend fun getCategories(): List<String>
    
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<ProductDto>
}
