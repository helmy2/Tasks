package com.example.tasks.presentation.account.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasks.data.util.Result

@Composable
fun LoginField(
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailErrorState by remember { mutableStateOf(false) }
    var passwordErrorState by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = "Login", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = email,
            placeholder = { Text(text = "Email", color = Color.Gray) },
            onValueChange = {
                if (emailErrorState) emailErrorState = false
                email = it
            },
            isError = emailErrorState,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        OutlinedTextField(
            value = password,
            placeholder = { Text(text = "Password", color = Color.Gray) },
            isError = passwordErrorState,
            onValueChange = {
                if (passwordErrorState) passwordErrorState = false
                password = it
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = {
                onRegisterClick()

            }) {
                Text(text = "Register")
            }
            Button(onClick = {
                if (email.isBlank())
                    emailErrorState = true
                if (password.isBlank())
                    passwordErrorState = true
                if (emailErrorState && emailErrorState)
                    onLoginClick(email, password)
            }) {
                Text(text = "Login")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFieldPreview() {
    LoginField(onLoginClick = { email, password -> }, onRegisterClick = {})
}