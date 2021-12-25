package com.example.tasks.presentation.addList.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.home.components.TaskItem
import com.example.tasks.presentation.util.Screen
import com.example.tasks.presentation.util.colorList
import com.google.gson.Gson

@Composable
fun AddListField(
    onBackClick: () -> Unit,
    onAddClick: (String, String, Int) -> Unit,
    onCheckItemClick: (task: Task) -> Unit,
    onDeleteItemClick: (id: Int) -> Unit,
    onTaskItemClick: (task: Task) -> Unit,
    taskList: TaskList?,
    list: List<Task>?,
) {

    val focusManager = LocalFocusManager.current
    var listName by remember { mutableStateOf(taskList?.title ?: "") }
    var listDescription by remember { mutableStateOf(taskList?.description ?: "") }

    val taskListIndex = colorList.indexOfFirst { it.name == taskList?.color }
    var listColorIndex by remember { mutableStateOf(if (taskListIndex == -1) 0 else taskListIndex) }

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
                onClick = onBackClick
            ) {
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

        list?.let {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                LazyColumn {
                    items(it) {
                        TaskItem(task = it, onCheckItemClick, onDeleteItemClick, onTaskItemClick)
                    }
                }
            }

        }


        Button(onClick = { onAddClick(listName, listDescription, listColorIndex) }) {
            Text(text = "Save")
        }
    }
}