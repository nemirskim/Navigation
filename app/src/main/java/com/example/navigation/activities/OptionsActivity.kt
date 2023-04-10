package com.example.navigation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContract
import com.example.navigation.databinding.ActivityOptionsBinding
import com.example.navigation.models.Options

class OptionsActivity : BaseActivity() {
    private var options: Options = Options.DEFAULT
    private var spinnerValues = listOf(2, 3, 4, 5, 6)
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var adapter: ArrayAdapter<Int>

    private val resultIntent: Intent
        get() = Intent().apply {
            putExtra(EXTRA_OUTPUT_OPTIONS, binding.fistCountSpinner.selectedItem.toString().toInt())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setupSpinner()

        binding.okButton.setOnClickListener { onOkPressed() }
    }

    private fun setupSpinner() = with(binding) {
        adapter = ArrayAdapter(
            this@OptionsActivity,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerValues
        )
        fistCountSpinner.adapter = adapter
        val spinnerPosition = spinnerValues.indexOfFirst {
                it == intent.getIntExtra(EXTRA_INPUT_OPTIONS, Options.DEFAULT.fistCount)
            }
        fistCountSpinner.setSelection(spinnerPosition)

        fistCountSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val count = spinnerValues[position]
                options = options.copy(fistCount = count)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun onOkPressed() {
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    class Contract : ActivityResultContract<Int, Options>() {
        override fun createIntent(context: Context, input: Int) =
            Intent(context, OptionsActivity::class.java).apply {
                putExtra(EXTRA_INPUT_OPTIONS, input)
            }

        override fun parseResult(resultCode: Int, intent: Intent?): Options {
            val settings = intent?.getIntExtra(EXTRA_OUTPUT_OPTIONS, Options.DEFAULT.fistCount)
                ?: Options.DEFAULT.fistCount
            return Options(settings)
        }
    }

    companion object {
        private const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        private const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}