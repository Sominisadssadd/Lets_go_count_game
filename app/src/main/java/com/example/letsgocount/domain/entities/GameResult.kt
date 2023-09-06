package com.example.letsgocount.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestion: Int,
    val gameSettings: GameSettings
) : Parcelable