package com.example.tasks.presentation.home.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NameField(name: String) {
    if (name.isNotEmpty())
        Text(
            text = "What's up, ${name.substringBefore(' ')}!    ",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
}