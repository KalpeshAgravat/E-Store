package com.andy.fakestore.feature_home.domain

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Double,
    val ratingCount: Int
)
