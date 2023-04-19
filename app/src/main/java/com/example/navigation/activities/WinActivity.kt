package com.example.navigation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.databinding.ActivityWinBinding

class WinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.toMainMenuButton.setOnClickListener { onToMainMenuPressed() }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Log", "WinActivity's onDestroy() called")
    }
    
    private fun onToMainMenuPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}