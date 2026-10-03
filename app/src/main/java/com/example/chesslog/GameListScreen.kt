package com.example.chesslog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameListScreen(
    viewModel: GameViewModel,
    onBack: () -> Unit,
    onAddGame: () -> Unit,
    onEditGame: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // HEADER
        Text(
            text = "♟ My Chess Games",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Your recorded chess games",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // BACK BUTTON
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Back")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // GAME LIST
        if (viewModel.games.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "♟",
                    style = MaterialTheme.typography.displayMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "No games recorded yet.",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Add your first chess game below."
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(viewModel.games) { game ->

                    GameCard(
                        game = game,

                        onEdit = {
                            onEditGame(game.id)
                        },

                        onDelete = {
                            viewModel.deleteGame(game.id)
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // ADD GAME BUTTON
        Button(
            onClick = onAddGame,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Add Game")
        }
    }
}


@Composable
fun GameCard(
    game: Game,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // OPPONENT
            Text(
                text = "♟  ${game.opponent}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // RESULT
            Text(
                text = "Result: ${game.result}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // OPENING
            Text(
                text = "Opening: ${game.opening}"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // TOURNAMENT
            Text(
                text = "Tournament: ${game.tournament}"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // BUTTONS
            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = onEdit,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }

                OutlinedButton(
                    onClick = onDelete,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}