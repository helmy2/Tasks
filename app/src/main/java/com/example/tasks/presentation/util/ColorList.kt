package com.example.tasks.presentation.util

import androidx.compose.ui.graphics.Color

sealed class ColorList(val name: String, val color: Color) {
    object Blue : ColorList(name = "Blue", color = Color.Blue)
    object Red : ColorList(name = "Red", color = Color.Red)
    object Gray : ColorList(name = "Gray", color = Color.Gray)
    object Cyan : ColorList(name = "Cyan", color = Color.Cyan)
    object Magenta : ColorList(name = "Magenta", color = Color.Magenta)
    object Green : ColorList(name = "Green", color = Color.Green)
    object Yellow : ColorList(name = "Yellow", color = Color.Yellow)
}