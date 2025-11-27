package com.andy.fakestore.feature_home.domain

import com.andy.fakestore.core.utils.Resource
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(category: String? = null): Resource<List<Product>> {
        return if (category == null || category == "All") {
            repository.getProducts()
        } else {
            repository.getProductsByCategory(category)
        }
    }
}
