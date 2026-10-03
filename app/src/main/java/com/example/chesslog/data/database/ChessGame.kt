package com.example.chesslog.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chess_games")
data class ChessGame(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val opponent: String,
    val result: String,
    val opening: String,
    val tournament: String
)