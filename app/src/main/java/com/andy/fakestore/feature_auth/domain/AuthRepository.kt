package com.andy.fakestore.feature_auth.domain

import com.andy.fakestore.core.utils.Resource

interface AuthRepository {
    suspend fun login(username: String, password: String): Resource<String>
}
