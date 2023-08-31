package com.example.letsgocount.domain.usecases

import com.example.letsgocount.domain.entities.Question
import com.example.letsgocount.domain.repository.GameRepository

class UseCaseGenerateQuestion(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateNewQuestion(maxSumValue, COUNT_ANSWERS)
    }

    companion object {
        private const val COUNT_ANSWERS = 6
    }
}