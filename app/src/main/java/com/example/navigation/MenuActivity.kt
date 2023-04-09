package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigation.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}