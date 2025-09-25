package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.socialautomation.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.logInBtn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }






    }
}