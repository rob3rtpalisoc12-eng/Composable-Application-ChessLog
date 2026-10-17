
package com.example.chesslog.screens

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


