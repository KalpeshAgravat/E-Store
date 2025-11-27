package com.andy.fakestore.feature_home.presentation

import com.andy.fakestore.feature_home.domain.Product

data class HomeState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val error: String? = null
)
