package com.andy.fakestore.feature_home.data

import com.andy.fakestore.core.utils.Resource
import com.andy.fakestore.feature_home.domain.Product
import com.andy.fakestore.feature_home.domain.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): Resource<List<Product>> {
        return try {
            val response = api.getProducts()
            Resource.Success(response.map { it.toDomain() })
        } catch (e: HttpException) {
            Resource.Error(e.message() ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun getProductDetails(id: Int): Resource<Product> {
        return try {
            val response = api.getProductDetails(id)
            Resource.Success(response.toDomain())
        } catch (e: HttpException) {
            Resource.Error(e.message() ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
    
    override suspend fun getCategories(): Resource<List<String>> {
        return try {
            val response = api.getCategories()
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Error(e.message() ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
    
    override suspend fun getProductsByCategory(category: String): Resource<List<Product>> {
        return try {
            val response = api.getProductsByCategory(category)
            Resource.Success(response.map { it.toDomain() })
        } catch (e: HttpException) {
            Resource.Error(e.message() ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }

    private fun ProductDto.toDomain(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating.rate,
            ratingCount = rating.count
        )
    }
}
