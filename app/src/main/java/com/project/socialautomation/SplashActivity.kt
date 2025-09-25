package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.project.socialautomation.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoarding1::class.java)
            startActivity(intent)
            finish()
        }
            , 2500)
    }
}