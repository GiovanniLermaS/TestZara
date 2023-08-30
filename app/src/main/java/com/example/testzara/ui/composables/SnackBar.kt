package com.example.testzara.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testzara.util.SnackbarVisualsWithError

@Composable
fun SnackBarData(data: SnackbarData) {
    val isError = (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
    val buttonColor = if (isError) {
        ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.error
        )
    } else {
        ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.inversePrimary
        )
    }

    Snackbar(modifier = Modifier
        .border(2.dp, MaterialTheme.colorScheme.secondary)
        .padding(12.dp), action = {
        TextButton(
            onClick = { if (isError) data.dismiss() else data.performAction() },
            colors = buttonColor
        ) { Text(data.visuals.actionLabel ?: "") }
    }) {
        Text(data.visuals.message)
    }
}