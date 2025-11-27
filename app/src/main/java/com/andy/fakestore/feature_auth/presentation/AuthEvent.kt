package com.andy.fakestore.feature_auth.presentation

sealed class AuthEvent {
    data class Login(val username: String, val password: String) : AuthEvent()
}
