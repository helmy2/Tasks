package com.example.tasks.presentation.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LogoutField(
    onLogoutClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { onLogoutClick() }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Sign out")
        }
    }
}