package com.project.socialautomation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.project.socialautomation.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenManager = TokenManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (tokenManager.getTwitterToken() != null) {
                startActivity(Intent(this, Home::class.java))
            } else {
                startActivity(Intent(this, OnBoarding1::class.java))
            }
            finish()
        }, 2500)
    }
}
