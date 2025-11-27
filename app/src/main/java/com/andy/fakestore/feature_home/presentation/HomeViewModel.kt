package com.andy.fakestore.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.fakestore.core.utils.Resource
import com.andy.fakestore.feature_home.domain.GetCategoriesUseCase
import com.andy.fakestore.feature_home.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCategories()
        getProducts("All")
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectCategory -> {
                getProducts(event.category)
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            when (val result = getCategoriesUseCase()) {
                is Resource.Success -> {
                    _state.value = state.value.copy(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    // Handle error silently or show snackbar
                }
                else -> {}
            }
        }
    }

    private fun getProducts(category: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true,
                selectedCategory = category,
                error = null
            )
            val result = getProductsUseCase(category)
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        products = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }
    }
}
