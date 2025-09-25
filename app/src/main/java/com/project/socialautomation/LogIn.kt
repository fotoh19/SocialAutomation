package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.socialautomation.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUPBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }
}