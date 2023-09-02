package com.example.letsgocount.domain.usecases

import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.domain.repository.GameRepository

class UseCaseGetGameSettings(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}