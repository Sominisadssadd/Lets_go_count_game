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
        initFileds()

    }

    private fun initFileds() {
        with(binding) {
            imageViewSmile.setImageResource(getImageResId())
            //NEW//
            textViewScoreAnswer.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswer
            )
            textViewRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswer
            )

            textViewRequiredScore.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswer
            )
            textViewScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightValue()
            )

        }
    }

    private fun getPercentOfRightValue() = with(gameResult) {
        if (countOfRightAnswer == 0) {
            0
        } else {
            ((countOfRightAnswer / countOfQuestion.toDouble()) * 100).toInt()
        }
    }

    private fun getImageResId(): Int {
        return when (gameResult.isWin) {
            true -> R.drawable.ic_smile
            false -> R.drawable.ic_sad
        }
    }

    private fun initialButtonOnBackPressed() {
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
        parseDataToFields()
    }


    private fun parseDataToFields() {
        if (gameResult.isWin) {
            binding.imageViewSmile.setImageResource(R.drawable.ic_smile)
        }

        binding.apply {
            textViewRequiredScore.text =
                gameResult.gameSettings.minCountOfRightAnswer.toString()
            textViewScoreAnswer.text = gameResult.countOfRightAnswer.toString()
            textViewRequiredPercentage.text =
                gameResult.gameSettings.minPercentOfRightAnswer.toString()

            val percentOfRightAnswer =
                (gameResult.countOfRightAnswer / 100) * gameResult.countOfQuestion * 100
            textViewScorePercentage.text = percentOfRightAnswer.toString()
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