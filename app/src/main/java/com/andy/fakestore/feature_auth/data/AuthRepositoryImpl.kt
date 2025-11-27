package com.andy.fakestore.feature_auth.data

import com.andy.fakestore.core.preferences.PreferenceManager
import com.andy.fakestore.core.utils.Resource
import com.andy.fakestore.feature_auth.domain.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
    private val prefs: PreferenceManager
) : AuthRepository {

    override suspend fun login(username: String, password: String): Resource<String> {
        return try {
            val response = api.login(LoginRequest(username, password))
            prefs.saveToken(response.token)
            Resource.Success(response.token)
        } catch (e: HttpException) {
            Resource.Error(e.message() ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: Exception) {
            Resource.Error("An unknown error occurred")
        }
    }
}
