package com.example.tasks.presentation.login

data class LoginState(
    val error: String? = null,
    val logged: Boolean = false,
    val progress: Boolean = false,
    val userName: String = "",
    val userEmail: String = "",
)