package com.andy.fakestore.feature_cart.data.repository

import com.andy.fakestore.feature_cart.data.local.dao.CartDao
import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
) : CartRepository {

    override fun getAllCartItems(): Flow<List<CartEntity>> {
        return dao.getAllCartItems()
    }

    override suspend fun insertCartItem(cartItem: CartEntity) {
        dao.insertCartItem(cartItem)
    }

    override suspend fun updateCartItem(cartItem: CartEntity) {
        dao.updateCartItem(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartEntity) {
        dao.deleteCartItem(cartItem)
    }

    override suspend fun deleteAllCartItems() {
        dao.deleteAllCartItems()
    }

    override suspend fun getCartItemByProductId(productId: Int): CartEntity? {
        return dao.getCartItemByProductId(productId)
    }
}
