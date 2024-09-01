package com.nekivai.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class AuthorizationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context ?: requireContext()).apply {
            setContent {

                MaterialTheme {
                    AuthScene(
                        login = remember { mutableStateOf("") },
                        token = remember { mutableStateOf("") },
                    )
                }
            }
        }
    }

    companion object {

        fun getInstance() = AuthorizationFragment()
    }
}