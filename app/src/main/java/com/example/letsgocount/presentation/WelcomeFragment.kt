package com.example.letsgocount.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentWelcomeBinding



class WelcomeFragment : Fragment() {

    //Такой подход работы с viewBinding правильный
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUnderstand.setOnClickListener {
            launchChooseLevelFragment()
        }

    }

    private fun launchChooseLevelFragment() {
        findNavController().navigate(R.id.action_welcomeFragment2_to_chooseLevelFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}