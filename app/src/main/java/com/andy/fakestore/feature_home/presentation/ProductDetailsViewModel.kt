package com.andy.fakestore.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.fakestore.core.utils.Resource
import com.andy.fakestore.feature_home.domain.GetProductDetailsUseCase
import com.andy.fakestore.feature_home.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addCartItemUseCase: com.andy.fakestore.feature_cart.domain.usecase.AddCartItemUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<Product?>(null)
    val state: State<Product?> = _state

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        savedStateHandle.get<String>("productId")?.let { productId ->
            getProductDetails(productId.toInt())
        }
    }

    private fun getProductDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = getProductDetailsUseCase(id)) {
                is Resource.Success -> {
                    _state.value = result.data
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            addCartItemUseCase(
                com.andy.fakestore.feature_cart.data.local.entity.CartEntity(
                    productId = product.id,
                    title = product.title,
                    price = product.price,
                    image = product.image,
                    quantity = 1
                )
            )
        }
    }
}
