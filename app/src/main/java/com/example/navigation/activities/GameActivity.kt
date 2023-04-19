package com.example.navigation.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.navigation.R
import com.example.navigation.databinding.ActivityGameBinding
import com.example.navigation.databinding.ItemFistBinding
import com.example.navigation.models.Options
import kotlin.properties.Delegates

class GameActivity : BaseActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var options: Options
    private var winningFist by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_OUTPUT_OPTIONS, Options::class.java) ?: Options.DEFAULT
        } else {
            intent.getParcelableExtra(EXTRA_OUTPUT_OPTIONS) ?: Options.DEFAULT
        }

        setupUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Log", "GameActivity's onDestroy() called")
    }

    private fun setupUi() {
        binding.countTextView.text = options.fistCount.toString()

        getWinningFist()
        createFists()
    }

    private fun createFists() {
        val fistBindings = (0 until options.fistCount).map { index ->
            val fistBinding = ItemFistBinding.inflate(layoutInflater)
            with(fistBinding) {
                root.id = View.generateViewId()
                root.setOnClickListener { view -> onFistSelected(view) }
                fistTitleTextView.text =
                    resources.getString(R.string.fist_number, (index + 1).toString())
                root.tag = index
            }
            binding.root.addView(fistBinding.root)
            fistBinding
        }

        binding.flow.referencedIds = fistBindings.map { it.root.id }.toIntArray()
    }

    private fun getWinningFist() {
        winningFist = (0 until options.fistCount).random()
    }

    private fun onFistSelected(view: View) {
        if (view.tag == winningFist) {
            val intent = Intent(this, WinActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}