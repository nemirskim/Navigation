package com.example.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.aboutButton.setOnClickListener { onAboutPressed() }
        binding.exitButton.setOnClickListener { onExitPressed() }
    }

    private fun onAboutPressed() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun onExitPressed() = finish()
}