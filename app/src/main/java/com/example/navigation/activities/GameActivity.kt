package com.example.navigation.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
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

    class Contract : ActivityResultContract<Options, Options>() {
        override fun createIntent(context: Context, input: Options) =
            Intent(context, GameActivity::class.java).apply {
                putExtra(EXTRA_INPUT_OPTIONS, input)
            }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(EXTRA_OUTPUT_OPTIONS, Options::class.java) ?: Options.DEFAULT
            } else {
                intent?.getParcelableExtra(EXTRA_OUTPUT_OPTIONS) ?: Options.DEFAULT
            }
    }

    companion object {
        private const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        private const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}