package com.example.tasks.presentation.task.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.tasks.presentation.util.FABItem
import com.example.tasks.presentation.util.MultiFloatingActionButton

@Composable
fun AddTaskField(
    navController: NavHostController,
    task: Task?,
    list: List<TaskList>,
    onItemClick: (id: Int?, taskName: String, taskDescription: String, selectedId: Int, date: Long) -> Unit,
    onItemDeletedClick: (id: Int) -> Unit
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

    Button(
        onClick = {
            onItemClick(
                null,
                taskName,
                taskDescription,
                list[selectedIndex].id!!,
                date
            )
        }
    ) {
        Text(text = if (task == null) "Save" else "Duplicate")
    }

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                list = if (task != null)
                    listOf(
                        FABItem("Update", Icons.Default.Update) {
                            onItemClick(
                                task.id, taskName, taskDescription, list[selectedIndex].id!!, date
                            )
                        },
                        FABItem("Duplicate", Icons.Default.ArrowForward) {
                            onItemClick(
                                null, taskName, taskDescription, list[selectedIndex].id!!, date
                            )
                        },
                        FABItem("Delete", Icons.Default.Delete) {
                            onItemDeletedClick(task.id!!)
                        }
                    ) else
                    listOf(
                        FABItem("Save", Icons.Default.Save) {
                            onItemClick(
                                task?.id, taskName, taskDescription, list[selectedIndex].id!!, date
                            )
                        },
                    )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
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
                    text = "Task", style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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
            }

        }
    }
}
