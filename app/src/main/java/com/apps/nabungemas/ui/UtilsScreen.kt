package com.apps.nabungemas.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apps.nabungemas.ui.theme.MyApplicationTheme

@Composable
fun DeletedConfirmationAlert(modifier: Modifier,
                             onCorfirm:(Boolean)->Unit
) {
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Delete Item", style = MaterialTheme.typography.h6) },
        text = { Text(text = "Do you want to deleted?") },
        backgroundColor = MaterialTheme.colors.background,
        dismissButton = {
            TextButton(onClick = { onCorfirm(false) }) {
                Text(text = "No")

            }
        },
        confirmButton = {
            TextButton(onClick = { onCorfirm(true) }) {
                Text(text = "Yes")

            }
        })
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    MyApplicationTheme(darkTheme = false) {
        DeletedConfirmationAlert(modifier = Modifier, onCorfirm = {})
    }
}