package com.andy.fakestore.feature_home.data

import com.google.gson.annotations.SerializedName

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
)

data class RatingDto(
    val rate: Double,
    val count: Int
)
