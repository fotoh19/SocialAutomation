package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.socialautomation.databinding.OnBoarding1Binding
import kotlin.jvm.java

class OnBoarding1 : AppCompatActivity() {

    private lateinit var binding: OnBoarding1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = OnBoarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NextBtnOn1.setOnClickListener {
            val intent = Intent(this, OnBoarding2::class.java)
            startActivity(intent)
            finish()

        }
    }
}