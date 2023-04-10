package com.example.navigation.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.navigation.databinding.ActivityOptionsBinding

class OptionsActivity : BaseActivity() {
    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            mutableListOf(2, 3, 4, 5, 6)
        )
        binding.fistCountSpinner.adapter = adapter

        binding.okButton.setOnClickListener { onOkPressed() }
    }

    private fun onOkPressed() = finish()
}