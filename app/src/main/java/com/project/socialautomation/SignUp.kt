package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.project.socialautomation.databinding.ActivitySignUpBinding
import kotlin.jvm.java

@Suppress("DEPRECATION")
class SignUp : AppCompatActivity() {

    private val viewModel: TwitterAuthViewModel by viewModels()

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.checkIfLoggedIn()

        binding.SignupWithX.setOnClickListener {

            viewModel.signInWithTwitter(this)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.authState.collect { state ->
                when (state) {
                    is TwitterAuthViewModel.AuthState.Idle -> {}
                    is TwitterAuthViewModel.AuthState.Loading -> {
                        Toast.makeText(this@SignUp, "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    is TwitterAuthViewModel.AuthState.Success -> {
                        Toast.makeText(this@SignUp, "Welcome ${state.user.displayName}", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SignUp, Home::class.java))
                        finish()
                    }
                    is TwitterAuthViewModel.AuthState.Error -> {
                        Toast.makeText(this@SignUp, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


        binding.logInBtn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }






    }
}