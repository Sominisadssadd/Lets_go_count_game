package com.example.letsgocount.data

import com.example.letsgocount.domain.entities.GameSettings
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.domain.entities.Question
import com.example.letsgocount.domain.repository.GameRepository
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_FOR_QUESTION = 2
    private const val MIN_VISIBLE_NUMBER_VALUE = 1

    override fun generateNewQuestion(maxSum: Int, countOfAnswers: Int): Question {
//        val seedForRandom = System.currentTimeMillis()
        val sumNumber = Random.nextInt(MIN_SUM_FOR_QUESTION,maxSum + 1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_NUMBER_VALUE, sumNumber)
        //использует HashSet для того чтобы исключить возможность содержания одинаковых чисел
        val listOfAnswers = HashSet<Int>()
        val rightAnswer = sumNumber - visibleNumber
        listOfAnswers.add(rightAnswer)
        while (listOfAnswers.size < countOfAnswers) {
            val oneOfAnswer = (MIN_VISIBLE_NUMBER_VALUE..maxSum).random()
            listOfAnswers.add(oneOfAnswer)
        }

        return Question(sumNumber, visibleNumber, listOfAnswers.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        when (level) {
            Level.TEST -> {
                return GameSettings(
                    10,
                    3,
                    30,
                    10
                )
            }
            Level.EASY -> {
                return GameSettings(
                    10,
                    5,
                    50,
                    50
                )
            }
            Level.NORMAL -> {
                return GameSettings(
                    30,
                    10,
                    70,
                    40
                )
            }
            Level.HARD -> {
                return GameSettings(
                    50,
                    15,
                    90,
                    30
                )
            }
        }
    }


}