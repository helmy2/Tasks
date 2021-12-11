package com.example.tasks.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.presentation.login.LoginState
import com.example.tasks.presentation.util.Screen
import com.example.tasks.presentation.util.isEmailValid
import com.example.tasks.util.Constants.EMAIL_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: UserRepo
) : ViewModel() {

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private fun registerUser(
        name: String,
        email: String,
        password: String
    ) = viewModelScope.launch {
        if (name.isEmpty()) {
            _registerState.value = RegisterState(error = "Name is Empty!")
            return@launch
        }
        if (email.isEmpty()) {
            _registerState.value = RegisterState(error = "Email is Empty!")
            return@launch
        }
        if (!isEmailValid(email)) {
            _registerState.value = RegisterState(error = "Email is not Valid!")
            return@launch
        }
        if (password.isEmpty()) {
            _registerState.value = RegisterState(error = "Password is Empty!")
            return@launch
        }


        _registerState.value = RegisterState(progress = true)
        delay(500L)
        val newUser = User(name = name, email = email, password = password)
        val result = repo.createUser(newUser)
        _registerState.value = RegisterState(
            error = result.errorMessage,
            logged = result is Result.Success,
            progress = false,
        )
    }

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.RequestRegister -> {
                registerUser(name = event.name, email = event.email, password = event.password)
            }
            is RegisterEvent.RequestLogin -> {
                event.navController.navigate(Screen.LoginScreen.route)
            }
            is RegisterEvent.RequestBack -> {
                event.navController.navigate(Screen.LoginScreen.route)
            }
        }
    }

}