package com.example.letsgocount.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentFinishedGameBinding
import com.example.letsgocount.domain.entities.GameResult



class FinishedGameFragment : Fragment() {


    private val args: FinishedGameFragmentArgs by navArgs()
    private val gameResult
        get() = args.gameResult


    private var _binding: FragmentFinishedGameBinding? = null
    private val binding: FragmentFinishedGameBinding
        get() = _binding ?: throw RuntimeException("FragmentFinishedGame == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFinishedGameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewLifecycleOwner связывает обратный вызов с жизненным циклом фрагмента
        //что гарантирует нам выполнение действий при назад тогда когда видим данный фрагмент

        initialButtonOnBackPressed()
        binding.gameResult = gameResult

    }
    private fun initialButtonOnBackPressed() {
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}