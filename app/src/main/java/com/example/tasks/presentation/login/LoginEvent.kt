package com.example.tasks.presentation.login

import androidx.navigation.NavHostController

sealed class LoginEvent {
    data class RequestLogin(val email: String, val password: String) : LoginEvent()
    data class RequestRegister(val navController: NavHostController) : LoginEvent()
    data class RequestBack(val navController: NavHostController) : LoginEvent()
    object RequestSignOut : LoginEvent()
}
