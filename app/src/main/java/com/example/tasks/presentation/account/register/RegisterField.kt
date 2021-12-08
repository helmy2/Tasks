package com.example.tasks.presentation.account.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasks.data.util.Result

@Composable
fun RegisterField(
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameErrorState by remember { mutableStateOf(false) }
    var emailErrorState by remember { mutableStateOf(false) }
    var passwordErrorState by remember { mutableStateOf(false) }
    var confirmPasswordErrorState by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState),
    ) {

        Text(
            text = "Registration",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = name,
            placeholder = { Text(text = "Name", color = Color.Gray) },
            onValueChange = {
                if (nameErrorState) nameErrorState = false
                name = it
            },
            isError = nameErrorState,
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        if (nameErrorState) {
            Text(text = "Required", color = Color.Red)
        }

        OutlinedTextField(
            value = email,
            placeholder = { Text(text = "Email", color = Color.Gray) },
            onValueChange = {
                if (emailErrorState) emailErrorState = false
                email = it
            },
            isError = emailErrorState,
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        PasswordTextField(
            placeholder = "Password",
            password = password,
            passwordErrorState = passwordErrorState,
            onValueChange = {
                if (passwordErrorState) passwordErrorState = false
                password = it
            })

        PasswordTextField(
            placeholder = "Confirm Password",
            password = confirmPassword,
            passwordErrorState = confirmPasswordErrorState,
            onValueChange = {
                if (passwordErrorState) passwordErrorState = false
                password = it
            })

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = {
                onRegisterClick()

            }) {
                Text(text = "Register")
            }
            Button(onClick = {
                when {
                    name.isBlank() -> nameErrorState = true
                    email.isBlank() -> emailErrorState = true
                    password.isBlank() -> passwordErrorState = true
                    else -> onLoginClick(email, password)

                }
            }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
private fun PasswordTextField(
    placeholder: String,
    password: String,
    passwordErrorState: Boolean,
    onValueChange: (it: String) -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(true) }

    OutlinedTextField(
        value = password,
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        isError = passwordErrorState,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {
                Icon(
                    imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "visibility",
                    tint = Color.Red
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterFieldPreview() {
    RegisterField(onLoginClick = { email, password -> }, onRegisterClick = {})
}