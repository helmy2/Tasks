package com.example.tasks.presentation.util

import androidx.compose.ui.graphics.Color

sealed class ListColor(val name: String, val color: Color) {
    object Blue : ListColor(name = "Blue", color = Color.Blue)
    object Red : ListColor(name = "Red", color = Color.Red)
    object Gray : ListColor(name = "Gray", color = Color.Gray)
    object Cyan : ListColor(name = "Cyan", color = Color.Cyan)
    object Magenta : ListColor(name = "Magenta", color = Color.Magenta)
    object Green : ListColor(name = "Green", color = Color.Green)
    object Yellow : ListColor(name = "Yellow", color = Color.Yellow)
}

val colorList = listOf(
    ListColor.Blue,
    ListColor.Red,
    ListColor.Gray,
    ListColor.Cyan,
    ListColor.Magenta,
    ListColor.Green,
    ListColor.Yellow
)
