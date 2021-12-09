package com.example.tasks.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

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
    val passwordVisibility = remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_sign_in),
            contentDescription = null,
            modifier = Modifier.width(TextFieldDefaults.MinWidth * .9f),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "please sign in to your account",
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

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
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            placeholder = { Text(text = "Password", color = Color.Gray) },
            isError = passwordErrorState,
            onValueChange = {
                if (passwordErrorState) passwordErrorState = false
                password = it
            },
            singleLine = true,
            visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "visibility",
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier
            .width(TextFieldDefaults.MinWidth)
            .height(TextFieldDefaults.MinHeight),
            onClick = {
                if (email.isBlank())
                    emailErrorState = true
                if (password.isBlank())
                    passwordErrorState = true
                onLoginClick(email, password)
            }) {
            Text(
                text = "Login", style = MaterialTheme.typography.h5,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "Don't have an Account ")
            Text(
                text = "Register",
                modifier = Modifier.clickable { onRegisterClick() },
                color = MaterialTheme.colors.primary
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginFieldPreview() {
    LoginField(onLoginClick = { email, password -> }, onRegisterClick = {})
}