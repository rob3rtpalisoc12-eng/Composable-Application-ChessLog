package com.example.chesslog

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesslog.data.GameRepository
import com.example.chesslog.data.database.ChessDatabase
import com.example.chesslog.data.database.ChessGame
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    // Create the database
    private val database = ChessDatabase.getDatabase(application)

    // Create the repository
    private val repository = GameRepository(
        database.chessGameDao()
    )

    // Games displayed by the UI
    private val _games = mutableStateListOf<Game>()

    val games: List<Game>
        get() = _games


    init {
        loadGames()
    }


    // READ
    private fun loadGames() {

        viewModelScope.launch {

            val savedGames = repository.getAllGames()

            _games.clear()

            _games.addAll(
                savedGames.map { chessGame ->

                    Game(
                        id = chessGame.id,
                        opponent = chessGame.opponent,
                        result = chessGame.result,
                        opening = chessGame.opening,
                        tournament = chessGame.tournament
                    )
                }
            )
        }
    }


    // CREATE
    fun addGame(game: Game) {

        viewModelScope.launch {

            val chessGame = ChessGame(
                opponent = game.opponent,
                result = game.result,
                opening = game.opening,
                tournament = game.tournament
            )

            repository.insertGame(chessGame)

            loadGames()
        }
    }


    // UPDATE
    fun updateGame(game: Game) {

        viewModelScope.launch {

            val chessGame = ChessGame(
                id = game.id,
                opponent = game.opponent,
                result = game.result,
                opening = game.opening,
                tournament = game.tournament
            )

            repository.updateGame(chessGame)

            loadGames()
        }
    }


    // DELETE
    fun deleteGame(gameId: Int) {

        viewModelScope.launch {

            repository.deleteGame(gameId)

            loadGames()
        }
    }
}