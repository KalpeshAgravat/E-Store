package com.andy.fakestore.feature_cart.domain.repository

import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<CartEntity>>
    suspend fun insertCartItem(cartItem: CartEntity)
    suspend fun updateCartItem(cartItem: CartEntity)
    suspend fun deleteCartItem(cartItem: CartEntity)
    suspend fun deleteAllCartItems()
    suspend fun getCartItemByProductId(productId: Int): CartEntity?
}
