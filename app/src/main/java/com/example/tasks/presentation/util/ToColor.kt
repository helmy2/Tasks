package com.example.tasks.presentation.util

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    return when (this) {
        ColorList.Blue.name -> ColorList.Blue.color
        ColorList.Cyan.name -> ColorList.Cyan.color
        ColorList.Magenta.name -> ColorList.Magenta.color
        ColorList.Red.name -> ColorList.Red.color
        ColorList.Gray.name -> ColorList.Gray.color
        ColorList.Green.name -> ColorList.Green.color
        ColorList.Yellow.name -> ColorList.Yellow.color
        else -> {
            ColorList.Magenta.color
        }
    }
}