package com.example.letsgocount.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentGameBinding
import com.example.letsgocount.domain.entities.GameResult
import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.presentation.ChooseLevelFragment.Companion.CHOOSE_LEVEL_FRAGMENT


class GameFragment : Fragment() {

    private lateinit var level: Level
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewAnswer1.setOnClickListener {
            val gameResult = GameResult(
                true,
                10,
                10,
                GameSettings(0, 0, 0, 0)
            )
            launchGameResultFragment(gameResult)
        }
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FinishedGameFragment.newInstanceFinishedGameFragment(gameResult)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        const val GAME_FRAGMENT_NAME = "gameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstanceGameFragment(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

}