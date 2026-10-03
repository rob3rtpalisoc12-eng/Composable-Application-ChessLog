package com.example.chesslog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ChessLogApp()
            }
        }
    }
}
@Composable
fun ChessLogApp(
    viewModel: GameViewModel = viewModel()
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // HOME SCREEN
        composable("home") {

            HomeScreen(
                onViewGames = {
                    navController.navigate("games")
                },

                onAddGame = {
                    navController.navigate("add_game")
                }
            )
        }


        // GAME LIST SCREEN
        composable("games") {

            GameListScreen(
                viewModel = viewModel,

                onBack = {
                    navController.popBackStack()
                },

                onAddGame = {
                    navController.navigate("add_game")
                },

                onEditGame = { gameId ->
                    navController.navigate("edit_game/$gameId")
                }
            )
        }


        // ADD GAME SCREEN
        composable("add_game") {

            AddGameScreen(
                onSave = { game ->

                    viewModel.addGame(game)

                    navController.navigate("games") {
                        popUpTo("home")
                    }
                }
            )
        }


        // EDIT GAME SCREEN
        composable(
            route = "edit_game/{gameId}",
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val gameId =
                backStackEntry.arguments?.getInt("gameId") ?: -1

            val game = viewModel.games.find {
                it.id == gameId
            }

            if (game != null) {

                EditGameScreen(
                    game = game,

                    onUpdate = { updatedGame ->

                        viewModel.updateGame(updatedGame)

                        navController.navigate("games") {
                            popUpTo("games") {
                                inclusive = true
                            }
                        }
                    },

                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


@Composable
fun HomeScreen(
    onViewGames: () -> Unit,
    onAddGame: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "♟",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "ChessLog",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Your simple chess game record keeper.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onViewGames,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Games")
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(
                onClick = onAddGame,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Game")
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "Record. Review. Improve.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}