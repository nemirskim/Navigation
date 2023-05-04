package com.example.navigation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigation.BuildConfig
import com.example.navigation.R
import com.example.navigation.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        binding.okButton.setOnClickListener {
            val action = AboutFragmentDirections.actionAboutFragmentToMenuFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupUi() {
        binding.applicationNameTextView.text = getString(R.string.application_name, "Where is my coin?")
        binding.versionNameTextView.text = getString(R.string.version_number, BuildConfig.VERSION_NAME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Logs", "AboutFragment onDestroyView() is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Logs", "AboutFragment onDestroy() is called")
    }
}