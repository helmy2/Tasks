package com.example.tasks.presentation.home

data class HomeState(
    val error: String? = null,
    val logged: Boolean = false,
    val progress: Boolean = false,
)
