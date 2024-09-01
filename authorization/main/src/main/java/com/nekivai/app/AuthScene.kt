package com.nekivai.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nekivai.ui_kit.BaseButton
import com.nekivai.ui_kit.OutlinedTextField

@Preview
@Composable
private fun Main() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        AuthScene()
    }
}

@Composable
fun AuthScene(login: MutableState<String>? = null, token: MutableState<String>? = null) =
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = login?.value.orEmpty(),
                label = "Login"
            ) {
                login?.value = it
            }
            OutlinedTextField(
                value = token?.value.orEmpty(),
                label = "Token"
            ) {
                token?.value = it
            }
            BaseButton(label = "Login") {

            }
        }
    }