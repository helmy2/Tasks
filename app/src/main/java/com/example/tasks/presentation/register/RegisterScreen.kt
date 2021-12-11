package com.example.tasks.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.login.LoginEvent
import com.example.tasks.presentation.register.components.RegisterField
import com.example.tasks.presentation.util.BackButton
import com.example.tasks.presentation.util.Screen

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val loginState = viewModel.registerState.value
    val context = LocalContext.current

    LaunchedEffect(loginState.logged) {
        if (loginState.logged)
            viewModel.onEvent(RegisterEvent.RequestLogin(navController))
    }
    Box {
        BackButton { viewModel.onEvent(RegisterEvent.RequestBack(navController)) }
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
                RegisterField(
                    onRegisterClick = { name, email, password ->
                        viewModel.onEvent(RegisterEvent.RequestRegister(name, email, password))
                    },
                    onLoginClick = {
                        viewModel.onEvent(RegisterEvent.RequestLogin(navController))
                    }
                )
            }
        }

    }

}

