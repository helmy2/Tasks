package com.example.tasks.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.util.Constants.EMAIL_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepo
) : ViewModel() {

    private val _loginState = MutableSharedFlow<Result<String>>()
    val loginState: SharedFlow<Result<String>> = _loginState

    private val _currentUserState = MutableSharedFlow<Result<User>>()
    val currentUserState: SharedFlow<Result<User>> = _currentUserState

    fun loginUser(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginState.emit(Result.Loading())

        if (email.isEmpty() || password.isEmpty()) {
            _loginState.emit(Result.Error("Some Fields are empty"))
            return@launch
        }

        if (!isEmailValid(email)) {
            _loginState.emit(Result.Error("Email is not Valid!"))
            return@launch
        }

        val newUser = User(
            name = null,
            email = email,
            password = password
        )
        _loginState.emit(repo.login(newUser))
    }


    fun getCurrentUser() = viewModelScope.launch {
        _currentUserState.emit(Result.Loading())
        _currentUserState.emit(repo.getUser())
    }

    fun logout() = viewModelScope.launch {
        val result = repo.logout()
        if (result is Result.Success) {
            getCurrentUser()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_REGEX)
        return (email.isNotEmpty() && pattern.matcher(email).matches())
    }

}