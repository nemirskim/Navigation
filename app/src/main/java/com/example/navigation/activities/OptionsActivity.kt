package com.example.navigation.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.navigation.databinding.ActivityOptionsBinding
import com.example.navigation.models.Options

class OptionsActivity : BaseActivity() {
    private var options: Options = Options.DEFAULT
    private var spinnerValues = listOf(2, 3, 4, 5, 6)
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var adapter: ArrayAdapter<Int>

    private val resultIntent: Intent
        get() = Intent().apply {
            putExtra(EXTRA_OUTPUT_OPTIONS, binding.fistsCountSpinner.selectedItem.toString().toInt())
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
        fistsCountSpinner.adapter = adapter
        val fistsCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_INPUT_OPTIONS, Options::class.java)?.fistCount as Int
        } else {
            intent.getParcelableExtra<Options>(EXTRA_INPUT_OPTIONS)?.fistCount as Int
        }
        fistsCountSpinner.setSelection(spinnerValues.indexOfFirst { it == fistsCount })

        fistsCountSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    companion object {
        const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}