package com.example.chesslog.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChessGameDao {

    // CREATE
    @Insert
    suspend fun insertGame(game: ChessGame)

    // READ
    @Query("SELECT * FROM chess_games")
    suspend fun getAllGames(): List<ChessGame>

    // UPDATE
    @Update
    suspend fun updateGame(game: ChessGame)

    // DELETE
    @Query("DELETE FROM chess_games WHERE id = :gameId")
    suspend fun deleteGame(gameId: Int)
}
