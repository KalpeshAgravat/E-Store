package com.andy.fakestore.feature_auth.domain

import com.andy.fakestore.core.utils.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Resource<String> {
        if (username.isBlank() || password.isBlank()) {
            return Resource.Error("Username and password cannot be empty")
        }
        return repository.login(username, password)
    }
}
