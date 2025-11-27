package com.andy.fakestore.feature_auth.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val token: String? = null,
    val error: String? = null
)
