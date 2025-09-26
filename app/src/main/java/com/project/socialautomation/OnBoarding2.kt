package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.socialautomation.databinding.ActivityOnBoarding2Binding

class OnBoarding2 : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoarding2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.LogBtn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }
            binding.SignBtn.setOnClickListener {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
                finish()
            }
        }
    }