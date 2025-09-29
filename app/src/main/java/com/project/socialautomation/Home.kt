package com.project.socialautomation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.project.socialautomation.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var tokenManager: TokenManager
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenManager = TokenManager(this)

        // زرار نشر التويتة
        binding.PostBtn.setOnClickListener {
            val tweetText = binding.textInputEditText.text.toString().trim()
            if (tweetText.isNotEmpty()) {
                postTweet(tweetText)
            } else {
                Toast.makeText(this, "اكتب التويتة الأول", Toast.LENGTH_SHORT).show()
            }
        }

        // زرار تسجيل الخروج
        binding.SignOutBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("تأكيد")
                .setMessage("هل أنت متأكد إنك عايز تسجل الخروج؟")
                .setPositiveButton("نعم") { _, _ -> performLogout() }
                .setNegativeButton("لا", null)
                .show()
        }
    }

    private fun postTweet(tweetText: String) {
        lifecycleScope.launch {
            try {
                val helper = TwitterApiHelper(
                    this@Home,
                    apiKey = "YOUR_API_KEY",       // حط هنا API Key
                    apiSecret = "YOUR_API_SECRET" // وحط هنا Secret
                )

                val success = helper.postTweet(tweetText)

                if (success) {
                    Toast.makeText(this@Home, "تم نشر التويتة ✅", Toast.LENGTH_SHORT).show()
                    binding.textInputEditText.text?.clear()
                } else {
                    Toast.makeText(this@Home, "فشل النشر ❌", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@Home, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogout() {
        firebaseAuth.signOut()
        tokenManager.clearTokens()

        val intent = Intent(this, LogIn::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
