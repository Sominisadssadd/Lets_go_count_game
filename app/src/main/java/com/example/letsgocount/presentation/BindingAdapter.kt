package com.example.letsgocount.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.letsgocount.R
import com.example.letsgocount.domain.entities.GameResult


interface OnOptionClickListener {
    fun onOptionClick(number: Int)
}

//GAME RESULT FRAGMENT

@BindingAdapter("requireAnswer")
fun getRequireAnswer(textView: TextView, requireCountOfAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        requireCountOfAnswers
    )
}

@BindingAdapter("requirePercentage")

fun getRequirePercentage(textView: TextView, requirePercentageOfAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        requirePercentageOfAnswers
    )
}


@BindingAdapter("currentAnswer")
fun getCurrentCountAnswer(textView: TextView, currentCountAnswer: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        currentCountAnswer
    )
}

@BindingAdapter("currentPercentage")
fun getCurrentPercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightValue(gameResult)
    )
}


private fun getPercentOfRightValue(gameResult: GameResult): Int = with(gameResult) {
    return if (countOfRightAnswer == 0) {
        0
    } else {
        ((countOfRightAnswer / countOfQuestion.toDouble()) * 100).toInt()
    }
}


@BindingAdapter("srcByGameResult")
fun setImageSourceByGameResult(imageView: ImageView, isWIn: Boolean) {
    imageView.setImageResource(getImageResId(isWIn))
}

private fun getImageResId(isWin: Boolean): Int {
    return when (isWin) {
        true -> R.drawable.ic_smile
        false -> R.drawable.ic_sad
    }
}

//GAME FRAGMENT

@BindingAdapter("enoughCount")
fun getEnoughCountOfAnswers(textView: TextView, stateOfAnswers: Boolean) {
    textView.setTextColor(
        getColorByState(
            goodState = stateOfAnswers,
            context = textView.context
        )
    )
}


@BindingAdapter("enoughPrecent")
fun getEnoughPrecentOfAnswer(progressBar: ProgressBar, stateOfAnswers: Boolean) {
    val color = getColorByState(stateOfAnswers, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }

    val color = ContextCompat.getColor(context, colorResId)
    return color
}


@BindingAdapter("numberToText")
fun getNumberAsString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onClickListener")
fun onTextViewClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}
