package com.example.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.databinding.FragmentMenuBinding
import com.example.navigation.models.Options

class MenuFragment : BaseFragment("MenuFragment", R.layout.fragment_menu) {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private var fistsCount: Int = Options.DEFAULT.fistCount

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        binding.startGameButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToGameFragment(fistsCount)
            findNavController().navigate(action)
        }

        binding.optionsButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToOptionsFragment(fistsCount)
            findNavController().navigate(action)
        }

        binding.aboutButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToAboutFragment()
            findNavController().navigate(action)
        }

        binding.exitButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupUi() {
        (activity as AppCompatActivity?)!!.supportActionBar?.show()

        val liveData =
            findNavController().currentBackStackEntry?.savedStateHandle
                ?.getLiveData<Int>(OptionsFragment.FISTS_COUNT)
        liveData?.observe(viewLifecycleOwner) {
            if (it != null) {
                fistsCount = it
                liveData.value = null
            }
        }
    }
}