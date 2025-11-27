package com.andy.fakestore.feature_home.presentation

sealed class HomeEvent {
    data class SelectCategory(val category: String) : HomeEvent()
}
