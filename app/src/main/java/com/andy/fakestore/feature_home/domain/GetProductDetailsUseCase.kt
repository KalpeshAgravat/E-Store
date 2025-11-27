package com.andy.fakestore.feature_home.domain

import com.andy.fakestore.core.utils.Resource
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Resource<Product> {
        return repository.getProductDetails(id)
    }
}
