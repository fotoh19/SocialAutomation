package com.project.socialautomation

import android.app.Activity
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider

class AuthRepository( private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()) {

    fun signInWithTwitter(
        activity: Activity,
        onSuccess: (String, String) -> Unit,   // نرجع الاتنين
        onError: (String) -> Unit
    ) {
        val provider = OAuthProvider.newBuilder("twitter.com")
        val pendingResultTask = firebaseAuth.pendingAuthResult

        val handleResult: (AuthResult) -> Unit = { result ->
            val credential = result.credential as? OAuthCredential
            val token = credential?.accessToken ?: ""
            val secret = credential?.secret ?: ""

            if (token.isNotEmpty() && secret.isNotEmpty()) {
                onSuccess(token, secret)
            } else {
                onError("Missing Twitter token")
            }
        }

        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener { handleResult(it) }
                .addOnFailureListener { e -> onError(e.message ?: "Unknown error") }
        } else {
            firebaseAuth
                .startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { handleResult(it) }
                .addOnFailureListener { e -> onError(e.message ?: "Unknown error") }
        }
    }


    fun getCurrentUser() = firebaseAuth.currentUser

    fun signOut() {
        firebaseAuth.signOut()
    }

}