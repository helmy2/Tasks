package com.example.tasks.presentation.addTask.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.util.toColor
import com.example.tasks.domain.util.getCurrentDate

@Composable
fun AddTaskField(
    navController: NavHostController,
    list: List<TaskList>,
    onItemClick: (taskName: String, taskDescription: String, selectedId: Int, date: Long) -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var selectedId by remember { mutableStateOf(0) }
    var date by remember { mutableStateOf(getCurrentDate()) }

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
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Text(
                text = "Add Task", style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        OutlinedTextField(
            value = taskName,
            placeholder = { Text(text = "Task Title", color = Color.Gray) },
            onValueChange = {
                taskName = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1
        )
        OutlinedTextField(
            value = taskDescription,
            placeholder = { Text(text = "Task Description", color = Color.Gray) },
            onValueChange = {
                taskDescription = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        LazyRow {
            items(list) {
                TextButton(
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (it.id == selectedId) it.color.toColor() else it.color.toColor()
                            .copy(alpha = .3f)
                    ),
                    onClick = {
                        selectedId = it.id!!
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = it.title)
                }
            }
        }
        DateField(context, onDateChange = { date = it })
        Button(onClick = {
            onItemClick(taskName, taskDescription, selectedId, date)
        }) {
            Text(text = "Save")
        }
    }
}