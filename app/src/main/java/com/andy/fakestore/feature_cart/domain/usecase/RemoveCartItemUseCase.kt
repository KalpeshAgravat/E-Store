package com.andy.fakestore.feature_cart.domain.usecase

import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import javax.inject.Inject

class RemoveCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartEntity) {
        repository.deleteCartItem(cartItem)
    }
}
