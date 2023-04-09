package com.example.navigation

import android.os.Bundle
import com.example.navigation.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setupUi()

        binding.okButton.setOnClickListener { onOkPressed() }
    }

    private fun setupUi() {
        binding.applicationNameTextView.text = getString(R.string.application_name, "Where is my coin?")
        binding.versionNameTextView.text = getString(R.string.version_number, BuildConfig.VERSION_NAME)
    }

    private fun onOkPressed() = finish()
}