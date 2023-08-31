package com.example.letsgocount.domain.repository

import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.domain.entities.Question


interface GameRepository {

    fun generateNewQuestion(
        maxSum: Int,
        countOfAnswers: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}