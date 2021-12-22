package com.example.tasks.presentation.register

data class RegisterState(
    val error: String? = null,
    val logged: Boolean = false,
    val progress: Boolean = false,
)