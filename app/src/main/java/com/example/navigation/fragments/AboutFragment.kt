package com.example.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.BuildConfig
import com.example.navigation.R
import com.example.navigation.databinding.FragmentAboutBinding

class AboutFragment : BaseFragment("AboutFragment", R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        binding.okButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        binding.applicationNameTextView.text = getString(R.string.application_name, "Where is my coin?")
        binding.versionNameTextView.text = getString(R.string.version_number, BuildConfig.VERSION_NAME)
    }
}