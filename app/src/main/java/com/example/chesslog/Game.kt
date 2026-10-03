package com.example.chesslog

data class Game(
    val id: Int,
    val opponent: String,
    val result: String,
    val opening: String,
    val tournament: String
)