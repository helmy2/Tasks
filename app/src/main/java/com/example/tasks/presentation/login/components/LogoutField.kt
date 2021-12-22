package com.example.tasks.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun LogoutField(
    onLogoutClick: () -> Unit,
    name: String,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.h3,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        Button(onClick = { onLogoutClick() }) {
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
    )
}