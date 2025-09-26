@file:Suppress("DEPRECATION")

package com.project.socialautomation

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

@Suppress("DEPRECATION")
class TokenManager(context: Context)  {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "twitter_tokens",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTwitterTokens(token: String, secret: String) {
        prefs.edit().apply {
            putString("token", token)
            putString("secret", secret)
            apply()
        }
    }

    fun getTwitterToken(): Pair<String?, String?> {
        val token = prefs.getString("token", null)
        val secret = prefs.getString("secret", null)
        return Pair(token, secret)
    }

    fun clearTokens() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean {
        val token = prefs.getString("token", null)
        val secret = prefs.getString("secret", null)
        return !token.isNullOrEmpty() && !secret.isNullOrEmpty()
    }
}
