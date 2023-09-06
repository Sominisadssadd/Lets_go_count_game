package com.example.letsgocount.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentGameBinding
import com.example.letsgocount.domain.entities.GameResult
import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.domain.entities.Question
import com.example.letsgocount.presentation.ChooseLevelFragment.Companion.CHOOSE_LEVEL_FRAGMENT


class GameFragment : Fragment() {

    private lateinit var level: Level
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private val buttonsSetUp by lazy {
        mutableListOf<TextView>().apply {
            add(binding.textViewAnswer1)
            add(binding.textViewAnswer2)
            add(binding.textViewAnswer3)
            add(binding.textViewAnswer4)
            add(binding.textViewAnswer5)
            add(binding.textViewAnswer6)
        }
    }


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
        observerViewModel()
        initAllButtons()
        viewModel.startGame(level)

    }

    private fun observerViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.textViewSumNumber.text = it.sum.toString()
            binding.visibleNumber.text = it.leftNumber.toString()
            for (i in it.listOfAnswer.indices) {
                buttonsSetUp[i].text = it.listOfAnswer[i].toString()
            }
        }

        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameResultFragment(it)
        }

        viewModel.enoughCount.observe(viewLifecycleOwner) {
            binding.textViewPercentAnswer.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.textVewTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.textViewPercentAnswer.text = it
        }
    }


    private fun initAllButtons() {
        for (currentTextView in buttonsSetUp) {
            currentTextView.setOnClickListener {
                viewModel.chooseAnswer(currentTextView.text.toString().toInt())
            }
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }

        val color = ContextCompat.getColor(requireContext(), colorResId)
        return color
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