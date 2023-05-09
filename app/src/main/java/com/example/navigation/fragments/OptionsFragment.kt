package com.example.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.navigation.R
import com.example.navigation.databinding.FragmentOptionsBinding

class OptionsFragment : BaseFragment("OptionsFragment", R.layout.fragment_options) {
    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ArrayAdapter<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
    }

    private fun setupSpinner() = with(binding) {
        adapter = ArrayAdapter(
            this@OptionsFragment.requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayListOf(2, 3, 4, 5, 6)
        )
        fistsCountSpinner.adapter = adapter
        fistsCountSpinner.setSelection(1)
    }
}