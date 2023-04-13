package com.example.navigation.activities

import android.os.Build
import android.os.Bundle
import com.example.navigation.databinding.ActivityGameBinding
import com.example.navigation.models.Options

class GameActivity : BaseActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.countTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_OUTPUT_OPTIONS, Options::class.java)?.fistCount.toString()
        } else {
            intent.getParcelableExtra<Options>(EXTRA_OUTPUT_OPTIONS)?.fistCount.toString()
        }
    }

    companion object {
        const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}