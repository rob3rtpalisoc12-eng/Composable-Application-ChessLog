package com.example.chesslog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chesslog.Game

@Composable
fun AddGameScreen(
    onSave: (Game) -> Unit
) {

    var opponent by remember {
        mutableStateOf("")
    }

    var opening by remember {
        mutableStateOf("")
    }

    var tournament by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    var showError by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Add Chess Game"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        TextField(
            value = opponent,
            onValueChange = {
                opponent = it
                showError = false
            },
            label = {
                Text("Opponent")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        TextField(
            value = opening,
            onValueChange = {
                opening = it
                showError = false
            },
            label = {
                Text("Opening")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        TextField(
            value = tournament,
            onValueChange = {
                tournament = it
                showError = false
            },
            label = {
                Text("Tournament")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(
            text = "Result:"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (result == "Win") {

                Button(
                    onClick = {
                        result = "Win"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Win")
                }

            } else {

                OutlinedButton(
                    onClick = {
                        result = "Win"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Win")
                }
            }


            if (result == "Draw") {

                Button(
                    onClick = {
                        result = "Draw"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Draw")
                }

            } else {

                OutlinedButton(
                    onClick = {
                        result = "Draw"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Draw")
                }
            }


            if (result == "Loss") {

                Button(
                    onClick = {
                        result = "Loss"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Loss")
                }

            } else {

                OutlinedButton(
                    onClick = {
                        result = "Loss"
                        showError = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Loss")
                }
            }
        }


        if (showError) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "⚠ Please fill in all fields and select a result."
            )
        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Button(
            onClick = {

                if (
                    opponent.isBlank() ||
                    opening.isBlank() ||
                    tournament.isBlank() ||
                    result.isBlank()
                ) {

                    showError = true

                } else {

                    val newGame = Game(
                        id = 0,
                        opponent = opponent,
                        result = result,
                        opening = opening,
                        tournament = tournament
                    )

                    onSave(newGame)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Game")
        }
    }
}