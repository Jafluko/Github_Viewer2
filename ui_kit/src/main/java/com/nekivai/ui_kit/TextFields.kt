package com.nekivai.ui_kit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Main() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        Scaffold {
            Column {
                OutlinedTextField(
                    value = "",
                    label = "login",
                    onValueChange = {}
                )
            }
        }
    }
}

@Composable
fun OutlinedTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) = OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label) },
    singleLine = true,
)