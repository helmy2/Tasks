package com.example.tasks.presentation.account.login

data class LoginState(
    val error: String? = null,
    val logged: Boolean = false,
    val progress: Boolean = false,
)