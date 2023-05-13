package com.example.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.databinding.FragmentGameBinding
import com.example.navigation.databinding.ItemFistBinding
import kotlin.properties.Delegates

class GameFragment : BaseFragment("GameFragment", R.layout.fragment_game) {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var fistsCount by Delegates.notNull<Int>()
    private var winningFist by Delegates.notNull<Int>()

    companion object {
        private const val FISTS_COUNT = "fistsCount"
        private const val WINNING_POS = "winningPos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            fistsCount = it.getInt(FISTS_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getWinningPosition(savedInstanceState)
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(WINNING_POS, winningFist)
    }

    private fun setupUi() {
        binding.fistsCountTextView.text = fistsCount.toString()
        createFists()
    }

    private fun createFists() {
        val fistsBinding = (0 until fistsCount).map { index ->
            val fistBinding = ItemFistBinding.inflate(layoutInflater)
            with(fistBinding) {
                root.id = View.generateViewId()
                fistTitleTextView.text =
                    resources.getString(R.string.fist_number, (index + 1).toString())
                root.setOnClickListener { view -> onFistPressed(view) }
                root.tag = index
            }
            binding.root.addView(fistBinding.root)
            fistBinding
        }

        binding.fistsFlow.referencedIds = fistsBinding.map { it.root.id }.toIntArray()
    }

    private fun onFistPressed(view: View) {
        if (view.tag == winningFist) {
            val action = GameFragmentDirections.actionGameFragmentToWinFragment()
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Defeat", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getWinningPosition(savedInstanceState: Bundle?) {
        winningFist = savedInstanceState?.getInt(WINNING_POS) ?: (0 until fistsCount).random()
    }
}