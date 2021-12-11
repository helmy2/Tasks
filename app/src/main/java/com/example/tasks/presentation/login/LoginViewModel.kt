package com.example.tasks.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.presentation.util.Screen
import com.example.tasks.presentation.util.isEmailValid
import com.example.tasks.util.Constants.EMAIL_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: UserRepo
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        getCurrentUser()
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.RequestLogin -> {
                loginUser(email = event.email, password = event.password)
            }
            is LoginEvent.RequestSignOut -> {
                logout()
            }
            is LoginEvent.RequestRegister -> {
                event.navController.navigate(Screen.RegisterScreen.route)
            }
            is LoginEvent.RequestBack -> {
                event.navController.navigate(Screen.HomeScreen.route)
            }

        }
    }

    private fun loginUser(
        email: String,
        password: String
    ) = viewModelScope.launch {

        if (email.isEmpty()) {
            _loginState.value = LoginState(error = "Email is Empty!")
            return@launch
        }
        if (!isEmailValid(email)) {
            _loginState.value = LoginState(error = "Email is not Valid!")
            return@launch
        }
        if (password.isEmpty()) {
            _loginState.value = LoginState(error = "Password is Empty!")
            return@launch
        }

        _loginState.value = LoginState(progress = true)
        delay(500L)
        val newUser = User(email = email, password = password)
        val result = repo.login(newUser)
        _loginState.value = LoginState(
            error = result.errorMessage,
            logged = result is Result.Success,
            progress = false,
        )

    }

    private fun getCurrentUser() = viewModelScope.launch {
        _loginState.value = LoginState(progress = true)
        val result = repo.getUser()
        _loginState.value = LoginState(logged = result is Result.Success)
    }

    private fun logout() = viewModelScope.launch {
        _loginState.value = LoginState(progress = true)
        delay(500L)
        val result = repo.logout()
        if (result is Result.Success)
            _loginState.value = LoginState()
    }


}