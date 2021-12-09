package com.example.tasks.presentation.login.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LogoutField(
    onLogoutClick: () -> Unit
) {
    Button(onClick = { onLogoutClick() }) {
        Text(text = "Sign out")
    }
}