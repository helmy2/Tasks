package com.example.tasks.presentation.addList.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddListField(
    onBackClick: () -> Unit,
    onAddClick: (String, String, Int) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    var listName by remember { mutableStateOf("") }
    var listDescription by remember { mutableStateOf("") }
    var listColorIndex by remember { mutableStateOf(0) }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Text(
                text = "Add List", style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        ListColorField(listColorIndex, onItemClickListener = { listColorIndex = it })

        OutlinedTextField(
            value = listName,
            placeholder = { Text(text = "List Name", color = Color.Gray) },
            onValueChange = {
                listName = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1
        )
        OutlinedTextField(
            value = listDescription,
            placeholder = { Text(text = "List Description", color = Color.Gray) },
            onValueChange = {
                listDescription = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Button(onClick = { onAddClick(listName, listDescription, listColorIndex) }) {
            Text(text = "Save")
        }
    }
}