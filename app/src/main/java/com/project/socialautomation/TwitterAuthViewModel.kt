package com.project.socialautomation

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TwitterAuthViewModel(
    private val repository: AuthRepository = AuthRepository()

) : ViewModel() {
    private var tokenManager: TokenManager? = null
    fun initTokenManager(context: Context) {
        tokenManager = TokenManager(context)
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val user: FirebaseUser) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun signInWithTwitter(activity: Activity) {
        _authState.value = AuthState.Loading
        repository.signInWithTwitter(
            activity,
            onSuccess = { token, secret ->
                tokenManager?.saveTwitterTokens(token, secret)
                val user = repository.getCurrentUser()
                if (user != null) {
                    _authState.value = AuthState.Success(user)
                } else {
                    _authState.value = AuthState.Error("User not found")
                }
            },
            onError = {
                _authState.value = AuthState.Error(it)
            }
        )
    }

    fun checkIfLoggedIn() {
        val user = repository.getCurrentUser()
        if (user != null) {
            _authState.value = AuthState.Success(user)
        }
    }

    fun signOut() {
        repository.signOut()
        _authState.value = AuthState.Idle
    }
}