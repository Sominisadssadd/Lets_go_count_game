package com.example.letsgocount.domain.entities

import java.io.Serializable

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswer: Int,
    val minPercentOfRightAnswer: Int,
    val countOfSecond: Int
) : Serializable {
    //ТАКЖЕ, РАБОТАЕМ С ПОЛЯ КЛАССА В ЕГО ЖЕ МЕТОДАХ
}