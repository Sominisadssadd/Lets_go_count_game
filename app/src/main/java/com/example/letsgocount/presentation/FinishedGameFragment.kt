package com.example.letsgocount.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.FragmentManager
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentFinishedGameBinding
import com.example.letsgocount.domain.entities.GameResult
import com.example.letsgocount.presentation.ChooseLevelFragment.Companion.CHOOSE_LEVEL_FRAGMENT


class FinishedGameFragment : Fragment() {


    private lateinit var gameResult: GameResult
    private var _binding: FragmentFinishedGameBinding? = null
    private val binding: FragmentFinishedGameBinding
        get() = _binding ?: throw RuntimeException("FragmentFinishedGame == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()

    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })

        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.GAME_FRAGMENT_NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_GAME_RESULT = "result"

        fun newInstanceFinishedGameFragment(gameResult: GameResult): FinishedGameFragment {
            return FinishedGameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }


}