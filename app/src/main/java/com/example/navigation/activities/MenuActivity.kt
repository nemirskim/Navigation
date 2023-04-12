package com.example.navigation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.databinding.ActivityMenuBinding
import com.example.navigation.models.Options

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private var options: Options = Options.DEFAULT

    private val gameLauncher =
        registerForActivityResult(GameActivity.Contract()) {}

    private val optionsLauncher =
        registerForActivityResult(OptionsActivity.Contract()) {
            binding.fistsCountTextView.text = it.fistCount.toString()
            options = it
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.startGameButton.setOnClickListener { onStartGamePressed() }
        binding.optionsButton.setOnClickListener { onOptionsPressed() }
        binding.aboutButton.setOnClickListener { onAboutPressed() }
        binding.exitButton.setOnClickListener { onExitPressed() }
    }

    private fun onStartGamePressed() {
        gameLauncher.launch(options)
    }

    private fun onOptionsPressed() {
        optionsLauncher.launch(options)
    }

    private fun onAboutPressed() {
        val intent = Intent(this, AboutActivity::class.java)
        intent.putExtra("fists count", options.fistCount)
        startActivity(intent)
    }

    private fun onExitPressed() = finish()
}