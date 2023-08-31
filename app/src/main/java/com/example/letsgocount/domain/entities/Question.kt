package com.example.letsgocount.domain.entities

data class Question(
    val sum: Int,
    val leftNumber: Int,
    val listOfAnswer: List<Int>
)