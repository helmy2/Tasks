package com.example.tasks.presentation.util

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    return when (this) {
        ListColor.Blue.name -> ListColor.Blue.color
        ListColor.Cyan.name -> ListColor.Cyan.color
        ListColor.Magenta.name -> ListColor.Magenta.color
        ListColor.Red.name -> ListColor.Red.color
        ListColor.Gray.name -> ListColor.Gray.color
        ListColor.Green.name -> ListColor.Green.color
        ListColor.Yellow.name -> ListColor.Yellow.color
        else -> {
            ListColor.Magenta.color
        }
    }
}