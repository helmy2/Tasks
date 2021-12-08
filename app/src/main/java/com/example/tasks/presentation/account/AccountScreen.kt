package com.example.tasks.presentation.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasks.presentation.account.login.LoginEvent
import com.example.tasks.presentation.account.login.LoginField
import com.example.tasks.presentation.account.register.RegisterField

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.value
    Scaffold {
        if (loginState.progress) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = loginState.error ?: "" + loginState.logged)
                RegisterField(
                    onLoginClick = { email, password ->
                        viewModel.onEvent(LoginEvent.RequestLogin(email, password))
                    },
                    onRegisterClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    Surface(color = MaterialTheme.colors.background) {
        AccountScreen()
    }
}