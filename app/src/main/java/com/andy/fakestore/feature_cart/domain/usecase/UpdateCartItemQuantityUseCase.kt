package com.andy.fakestore.feature_cart.domain.usecase

import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartItemQuantityUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartEntity, newQuantity: Int) {
        if (newQuantity <= 0) {
            repository.deleteCartItem(cartItem)
        } else {
            repository.updateCartItem(cartItem.copy(quantity = newQuantity))
        }
    }
}
