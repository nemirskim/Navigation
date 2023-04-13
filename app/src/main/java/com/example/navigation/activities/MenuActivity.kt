package com.example.navigation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.Contract
import com.example.navigation.databinding.ActivityMenuBinding
import com.example.navigation.models.Options

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private var options: Options = Options.DEFAULT

    private val gameLauncher =
        registerForActivityResult(
            Contract(
                GameActivity::class.java,
                GameActivity.EXTRA_INPUT_OPTIONS, GameActivity.EXTRA_OUTPUT_OPTIONS
            )
        ) {}

    private val optionsLauncher =
        registerForActivityResult(
            Contract(
                OptionsActivity::class.java,
                OptionsActivity.EXTRA_INPUT_OPTIONS, OptionsActivity.EXTRA_OUTPUT_OPTIONS
            )
        ) {
            binding.fistsCountTextView.text = it.fistCount.toString()
            options = it
            setupUi()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.startGameButton.setOnClickListener { onStartGamePressed() }
        binding.optionsButton.setOnClickListener { onOptionsPressed() }
        binding.aboutButton.setOnClickListener { onAboutPressed() }
        binding.exitButton.setOnClickListener { onExitPressed() }

        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
        setupUi()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    private fun setupUi() {
        binding.fistsCountTextView.text = options.fistCount.toString()
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

    companion object {
        private const val KEY_OPTIONS = "OPTIONS"
    }
}