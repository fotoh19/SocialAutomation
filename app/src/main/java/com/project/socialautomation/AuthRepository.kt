package com.project.socialautomation

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class AuthRepository( private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()) {

    fun signInWithTwitter(
        activity: Activity,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val provider = OAuthProvider.newBuilder("twitter.com")
        val pendingResultTask = firebaseAuth.pendingAuthResult

        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener { result ->
                    val userId = result.user?.uid ?: ""
                    onSuccess(userId)
                }
                .addOnFailureListener { e ->
                    onError(e.message ?: "Unknown error")
                }
        } else {
            firebaseAuth
                .startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { result ->
                    val userId = result.user?.uid ?: ""
                    onSuccess(userId)
                }
                .addOnFailureListener { e ->
                    onError(e.message ?: "Unknown error")
                }
        }
    }

    fun getCurrentUser() = firebaseAuth.currentUser

    fun signOut() {
        firebaseAuth.signOut()
    }

}