package com.andy.fakestore.feature_home.domain

import com.andy.fakestore.core.utils.Resource
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<String>> {
        return repository.getCategories()
    }
}
