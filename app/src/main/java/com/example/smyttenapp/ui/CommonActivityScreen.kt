package com.example.smyttenapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smyttenapp.theme.SmyttenAppTheme

@Composable
fun CommonActivityScreen(
    title: String,
    showDialog: Boolean,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    SmyttenAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Go Back")
            }
        }
        if (showDialog) {
            AlertDialog(onDismissRequest = onDismissRequest)
        }
    }
}