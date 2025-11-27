package com.andy.fakestore.feature_cart.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import com.andy.fakestore.feature_cart.domain.usecase.GetCartItemsUseCase
import com.andy.fakestore.feature_cart.domain.usecase.RemoveCartItemUseCase
import com.andy.fakestore.feature_cart.domain.usecase.UpdateCartItemQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase
) : ViewModel() {

    private val _state = mutableStateOf<List<CartEntity>>(emptyList())
    val state: State<List<CartEntity>> = _state

    private val _totalPrice = mutableStateOf(0.0)
    val totalPrice: State<Double> = _totalPrice

    init {
        getCartItems()
    }

    private fun getCartItems() {
        getCartItemsUseCase().onEach { items ->
            _state.value = items
            calculateTotal(items)
        }.launchIn(viewModelScope)
    }

    private fun calculateTotal(items: List<CartEntity>) {
        _totalPrice.value = items.sumOf { it.price * it.quantity }
    }

    fun updateQuantity(cartItem: CartEntity, newQuantity: Int) {
        viewModelScope.launch {
            updateCartItemQuantityUseCase(cartItem, newQuantity)
        }
    }

    fun removeItem(cartItem: CartEntity) {
        viewModelScope.launch {
            removeCartItemUseCase(cartItem)
        }
    }
}
