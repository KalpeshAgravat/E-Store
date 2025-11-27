package com.andy.fakestore.core.preferences

import android.content.Context
import android.content.SharedPreferences
import com.andy.fakestore.core.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString(Constants.TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return prefs.getString(Constants.TOKEN_KEY, null)
    }

    fun clearToken() {
        prefs.edit().remove(Constants.TOKEN_KEY).apply()
    }
}
