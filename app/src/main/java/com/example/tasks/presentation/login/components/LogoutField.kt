package com.example.tasks.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasks.presentation.list.DetailsCard

@Composable
fun LogoutField(
    onLogoutClick: () -> Unit,
    name: String,
    email: String,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Email")
        Text(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(bottom = 8.dp, start = 8.dp)
            ,
            text = email, style = MaterialTheme.typography.h5,
        )
        Text(text = "Name")
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(bottom = 16.dp, start = 8.dp)
            ,
            style = MaterialTheme.typography.h5,
        )

        Button(onClick = { onLogoutClick() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Sign out")
        }
    }
}

@Preview
@Composable
fun LogoutFieldPreview() {
    LogoutField(
        onLogoutClick = { },
        name = "Olivia Mitchell",
        email = " userEmail",
    )
}