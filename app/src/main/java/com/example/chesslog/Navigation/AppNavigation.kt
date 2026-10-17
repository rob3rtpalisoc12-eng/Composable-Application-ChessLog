
package com.example.chesslog.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.chesslog.GameViewModel
import com.example.chesslog.screens.AddGameScreen
import com.example.chesslog.screens.EditGameScreen
import com.example.chesslog.screens.GameListScreen
import com.example.chesslog.screens.HomeScreen

object AppRoutes {
    const val HOME = "home"
    const val GAMES = "games"
    const val ADD_GAME = "add_game"
    const val EDIT_GAME = "edit_game/{gameId}"

    fun editGame(gameId: Int): String {
        return "edit_game/$gameId"
    }
}

@Composable
fun AppNavigation(
    gameViewModel: GameViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {

        // HOME SCREEN
        composable(AppRoutes.HOME) {
            HomeScreen(
                onViewGames = {
                    navController.navigate(AppRoutes.GAMES)
                },
                onAddGame = {
                    navController.navigate(AppRoutes.ADD_GAME)
                }
            )
        }

        // GAME LIST SCREEN
        composable(AppRoutes.GAMES) {
            GameListScreen(
                viewModel = gameViewModel,
                onBack = {
                    navController.popBackStack()
                },
                onAddGame = {
                    navController.navigate(AppRoutes.ADD_GAME)
                },
                onEditGame = { gameId ->
                    navController.navigate(
                        AppRoutes.editGame(gameId)
                    )
                }
            )
        }

        // ADD GAME SCREEN
        composable(AppRoutes.ADD_GAME) {
            AddGameScreen(
                onSave = { game ->
                    gameViewModel.addGame(game)

                    navController.navigate(AppRoutes.GAMES) {
                        popUpTo(AppRoutes.HOME)
                    }
                }
            )
        }

        // EDIT GAME SCREEN
        composable(
            route = AppRoutes.EDIT_GAME,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val gameId =
                backStackEntry.arguments?.getInt("gameId") ?: -1

            val game = gameViewModel.games.find {
                it.id == gameId
            }

            if (game != null) {
                EditGameScreen(
                    game = game,
                    onUpdate = { updatedGame ->
                        gameViewModel.updateGame(updatedGame)

                        navController.navigate(AppRoutes.GAMES) {
                            popUpTo(AppRoutes.GAMES) {
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
