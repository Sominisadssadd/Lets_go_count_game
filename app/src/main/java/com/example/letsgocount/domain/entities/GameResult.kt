package com.example.letsgocount.domain.entities

data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestion: Int,
    val gameSettings: GameSettings
)