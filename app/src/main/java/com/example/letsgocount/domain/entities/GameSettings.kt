package com.example.letsgocount.domain.entities

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswer: Int,
    val minPercentOfRightAnswer: Int,
    val countOfSecond: Int
)