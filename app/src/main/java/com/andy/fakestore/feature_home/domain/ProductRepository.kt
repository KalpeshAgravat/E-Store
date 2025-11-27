package com.andy.fakestore.feature_home.domain

import com.andy.fakestore.core.utils.Resource

interface ProductRepository {
    suspend fun getProducts(): Resource<List<Product>>
    suspend fun getProductDetails(id: Int): Resource<Product>
    suspend fun getCategories(): Resource<List<String>>
    suspend fun getProductsByCategory(category: String): Resource<List<Product>>
}
