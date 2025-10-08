package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.project.socialautomation.databinding.ActivityLogInBinding
import kotlin.getValue

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val viewModel: TwitterAuthViewModel by viewModels()
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenManager = TokenManager(this)
        viewModel.initTokenManager(this)

        binding.XBtnLgIn.setOnClickListener {
            viewModel.signInWithTwitter(this)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.authState.collect { state ->
                when (state) {
                    is TwitterAuthViewModel.AuthState.Idle -> {}
                    is TwitterAuthViewModel.AuthState.Loading -> {
                        Toast.makeText(this@LogIn, "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    is TwitterAuthViewModel.AuthState.Success -> {
                        // ✅ احفظ التوكين هنا
                        val accessToken = state.user.providerData.find {
                            it.providerId == "twitter.com"
                        }

                        val token = accessToken?.uid ?: ""   // ده غالباً الـ userId
                        val secret = state.user.getIdToken(false).result?.token ?: ""

                        if (token.isNotEmpty() && secret.isNotEmpty()) {
                            tokenManager.saveTwitterTokens(token, secret)
                        }

                        Toast.makeText(
                            this@LogIn,
                            "Welcome ${state.user.displayName}",
                            Toast.LENGTH_LONG
                        ).show()

                        startActivity(Intent(this@LogIn, Home::class.java))
                        finish()
                    }
                    is TwitterAuthViewModel.AuthState.Error -> {
                        Toast.makeText(
                            this@LogIn,
                            "Error: ${state.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        binding.signUPBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }
}
