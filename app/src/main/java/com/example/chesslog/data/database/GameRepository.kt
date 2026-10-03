package com.example.chesslog.data

import com.example.chesslog.data.database.ChessGame
import com.example.chesslog.data.database.ChessGameDao

class GameRepository(
    private val dao: ChessGameDao
) {

    suspend fun getAllGames(): List<ChessGame> {
        return dao.getAllGames()
    }

    suspend fun insertGame(game: ChessGame) {
        dao.insertGame(game)
    }

    suspend fun updateGame(game: ChessGame) {
        dao.updateGame(game)
    }

    suspend fun deleteGame(gameId: Int) {
        dao.deleteGame(gameId)
    }
}

