package com.example.letsgocount.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentGameBinding
import com.example.letsgocount.domain.entities.GameResult
import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.domain.entities.Question


class GameFragment : Fragment() {


    private val args: GameFragmentArgs by navArgs()

    private val viewModelFactory: GameViewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application, args.level)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    private fun observerViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameResultFragment(it)
        }
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToFinishedGameFragment(gameResult)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}