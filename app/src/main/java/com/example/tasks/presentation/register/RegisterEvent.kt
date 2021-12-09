package com.example.tasks.presentation.register

import androidx.navigation.NavHostController

sealed class RegisterEvent {
    data class RequestLogin(val navController: NavHostController) : RegisterEvent()
    data class RequestRegister(val name: String, val email: String, val password: String) :
        RegisterEvent()
}
