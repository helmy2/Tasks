package com.example.tasks.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.login.components.LoginField
import com.example.tasks.presentation.login.components.LogoutField
import com.example.tasks.presentation.util.BackButton

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.value
    val context = LocalContext.current

    Box {
//        BackButton { viewModel.onEvent(LoginEvent.RequestBack(navController)) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            loginState.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            if (loginState.progress) {
                CircularProgressIndicator()
            } else {
                if (loginState.logged)
                    LogoutField(
                        onLogoutClick = {
                            viewModel.onEvent(LoginEvent.RequestSignOut)
                        },
                        name = loginState.userName,
                        email = loginState.userEmail
                    )
                else
                    LoginField(
                        onLoginClick = { email, password ->
                            viewModel.onEvent(LoginEvent.RequestLogin(email, password,navController))
                        },
                        onRegisterClick = {
                            viewModel.onEvent(LoginEvent.RequestRegister(navController))
                        },
                    )
            }
        }
    }
}

