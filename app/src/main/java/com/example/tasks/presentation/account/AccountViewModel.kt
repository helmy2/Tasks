package com.example.tasks.presentation.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.presentation.account.login.LoginEvent
import com.example.tasks.presentation.account.login.LoginState
import com.example.tasks.util.Constants.EMAIL_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: UserRepo
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private fun loginUser(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginState.value = when {
            email.isEmpty() -> LoginState(error = "Email is Empty!")
            !isEmailValid(email) -> LoginState(error = "Email is not Valid!")
            password.isEmpty() -> LoginState(error = "Password is Empty!")
            else -> {
                LoginState(progress = true)
                delay(500L)
                val newUser = User(name = null, email = email, password = password)
                val result = repo.login(newUser)
                LoginState(
                    error = result.errorMessage,
                    logged = result is Result.Success,
                    progress = false,
                )
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.RequestLogin -> {
                loginUser(email = event.email, password = event.password)
            }
            is LoginEvent.RequestRegister -> {

            }
        }
    }
//    fun getCurrentUser() = viewModelScope.launch {
//        _currentUserState.emit(Result.Loading())
//        _currentUserState.emit(repo.getUser())
//    }

//    fun logout() = viewModelScope.launch {
//        val result = repo.logout()
//        if (result is Result.Success) {
//            getCurrentUser()
//        }
//    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_REGEX)
        return (email.isNotEmpty() && pattern.matcher(email).matches())
    }

}