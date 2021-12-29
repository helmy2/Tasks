package com.example.tasks.presentation.addTask.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.util.getCurrentDate
import com.example.tasks.presentation.util.toColor

@Composable
fun AddTaskField(
    navController: NavHostController,
    task: Task?,
    list: List<TaskList>,
    onItemClick: (taskName: String, taskDescription: String, selectedId: Int, date: Long) -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var taskName by remember { mutableStateOf(task?.title ?: "") }
    var taskDescription by remember { mutableStateOf(task?.description ?: "") }
    var selectedIndex by remember { mutableStateOf(0) }
    var date by remember { mutableStateOf(task?.date ?: getCurrentDate()) }

    LaunchedEffect(key1 = true) {
        if (list.isNotEmpty() && task != null)
            selectedIndex = list.indexOfFirst { it.id == task.listId }
    }

    Column(
        Modifier
            .padding(16.dp)
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
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

        DropdownField(
            list,
            selectedIndex,
            onItemClick = { selectedIndex = it },
            modifier = Modifier.padding(top = 8.dp)
        )

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

        DateField(context, onDateChange = { date = it }, currantDate = date)
        Button(onClick = {
            onItemClick(taskName, taskDescription, list[selectedIndex].id!!, date)
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Save")
        }
    }
}

@Composable
private fun DropdownField(
    list: List<TaskList>,
    selectedIndex: Int,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    if (list.isNotEmpty())
        Box(modifier) {

            Row(modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = { expanded = true })
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "",
                    tint = list[selectedIndex].color.toColor().copy(alpha = .9f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(list[selectedIndex].title)
            }


            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEachIndexed { index, taskList ->
                    DropdownMenuItem(onClick = {
                        onItemClick(index)
                        expanded = false
                    }) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "",
                                tint = taskList.color.toColor().copy(alpha = .9f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(taskList.title)
                        }
                    }
                }
            }
        }
}

