package com.example.tasks.presentation.account.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.presentation.account.UserViewModel
import com.example.tasks.presentation.account.login.components.LoginField

@Composable
fun LoginScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsState(initial = Result.Loading())
    val userState by viewModel.currentUserState.collectAsState(initial = Result.Loading())
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    SideEffect {
        viewModel.getCurrentUser()
    }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(text = loginState.data + " " + loginState.errorMessage)
            Text(text = userState.data.toString() + " " + userState.errorMessage)

            LoginField(
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onLoginClick = { viewModel.loginUser(email, password) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Surface(color = MaterialTheme.colors.background) {
        LoginScreen()
    }
}