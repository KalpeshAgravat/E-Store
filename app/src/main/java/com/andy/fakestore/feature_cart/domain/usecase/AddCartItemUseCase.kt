package com.andy.fakestore.feature_cart.domain.usecase

import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import javax.inject.Inject

class AddCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartEntity) {
        val existingItem = repository.getCartItemByProductId(cartItem.productId)
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            repository.updateCartItem(updatedItem)
        } else {
            repository.insertCartItem(cartItem)
        }
    }
}
