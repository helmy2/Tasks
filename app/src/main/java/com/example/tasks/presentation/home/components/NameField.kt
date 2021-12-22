package com.example.tasks.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@Composable
fun NameField(name: String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (name.isNotEmpty())
            Column {
                Text(text = "Hello")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5
                )
            }

        IconButton(onClick = onClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile Icon",
            )
        }
    }
}