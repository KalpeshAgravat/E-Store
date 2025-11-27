package com.andy.fakestore.feature_cart.domain.usecase

import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<List<CartEntity>> {
        return repository.getAllCartItems()
    }
}
