package com.nekivai.ui_kit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Main() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        Scaffold {
            Column {
                BaseButton(label = "Btn") {}
            }
        }
    }
}

@Composable
fun BaseButton(
    label: String,
    onClick: () -> Unit,
) = OutlinedButton(onClick = onClick) {
    Text(text = label)
}