package com.example.tasks.presentation.account.login

sealed class LoginEvent {
    data class RequestLogin(val email: String, val password: String) : LoginEvent()
    data class RequestRegister(val name: String, val email: String, val password: String) :
        LoginEvent()
}
