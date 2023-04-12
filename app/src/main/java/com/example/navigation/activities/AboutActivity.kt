package com.example.navigation.activities

import android.os.Bundle
import com.example.navigation.BuildConfig
import com.example.navigation.R
import com.example.navigation.databinding.ActivityAboutBinding
import com.example.navigation.models.Options

class AboutActivity : BaseActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setupUi()

        binding.okButton.setOnClickListener { onOkPressed() }
    }

    private fun setupUi() = with(binding) {
        applicationNameTextView.text = getString(R.string.application_name, "Where is my coin?")
        versionNameTextView.text = getString(R.string.version_number, BuildConfig.VERSION_NAME)
        fistCountTextView.text = resources.getString(
            R.string.fists_count,
            intent.getIntExtra("fists count", Options.DEFAULT.fistCount).toString()
        )
    }

    private fun onOkPressed() = finish()
}