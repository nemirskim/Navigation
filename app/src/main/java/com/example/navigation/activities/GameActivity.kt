package com.example.navigation.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.navigation.R
import com.example.navigation.databinding.ActivityGameBinding
import com.example.navigation.databinding.ItemFistBinding
import com.example.navigation.models.Options

class GameActivity : BaseActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var options: Options
//    private var fistIndex by Delegates.notNull<Int>()

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
    
    private fun setupUi() {
        binding.countTextView.text = options.fistCount.toString()

        createFists()
    }
    
    private fun createFists() {
        val fistBindings = (0 until options.fistCount).map { index ->
            val fistBinding = ItemFistBinding.inflate(layoutInflater)
            fistBinding.root.id = View.generateViewId()
            fistBinding.fistTitleTextView.text = resources.getString(R.string.fist_number, (index + 1).toString())
            fistBinding.root.setOnClickListener { view -> onFistSelected(view) }
            fistBinding.root.tag = index
            binding.root.addView(fistBinding.root)
            fistBinding
        }

        binding.flow.referencedIds = fistBindings.map { it.root.id }.toIntArray()
    }

    private fun onFistSelected(view: View) {
        Toast.makeText(this, "Yes!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_INPUT_OPTIONS = "EXTRA_OPTIONS"
        const val EXTRA_OUTPUT_OPTIONS = "EXTRA_OPTIONS"
    }
}