package com.noxis.composeexample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun DialogSearch(dialogState: MutableState<Boolean>, confirm: (String) -> Unit) {
    val dialogText = remember {
        mutableStateOf("Text")
    }
    AlertDialog(
        onDismissRequest = { dialogState.value = false },
        confirmButton = {
            Button(onClick = {
                confirm(dialogText.value)
                dialogState.value = false
            }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            Button(onClick = { dialogState.value = false }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Введите название города:")
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = it
                })
            }
        })
}