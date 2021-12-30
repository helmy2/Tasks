package com.example.tasks.presentation.taskList.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.tasks.presentation.util.colorList
import com.example.tasks.presentation.util.FABItem
import com.example.tasks.presentation.util.MultiFloatingActionButton

@Composable
fun AddListField(
    onBackClick: () -> Unit,
    onAddClick: (taskList: TaskList) -> Unit,
    onCheckItemClick: (task: Task) -> Unit,
    onDeleteTaskClick: (id: Int) -> Unit,
    onTaskItemClick: (task: Task) -> Unit,
    taskList: TaskList?,
    list: List<Task>?,
    onDeleteList: (id: Int) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    var listName by remember { mutableStateOf(taskList?.title ?: "") }
    var listDescription by remember { mutableStateOf(taskList?.description ?: "") }

    val taskListIndex = colorList.indexOfFirst { it.name == taskList?.color }
    var listColorIndex by remember { mutableStateOf(if (taskListIndex == -1) 0 else taskListIndex) }

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                list = if (taskList?.id != null)
                    listOf(
                        FABItem("Update", Icons.Default.Update) {
                            onAddClick(
                                TaskList(
                                    taskList.id,
                                    listName,
                                    listDescription,
                                    colorList[listColorIndex].name,
                                    null
                                )
                            )
                        },
                        FABItem("Delete", Icons.Default.Delete) {
                            onDeleteList(taskList.id)
                        }
                    ) else
                    listOf(
                        FABItem("Save", Icons.Default.Save) {
                            onAddClick(
                                TaskList(
                                    taskList?.id,
                                    listName,
                                    listDescription,
                                    colorList[listColorIndex].name,
                                    null
                                )
                            )
                        },
                    )
            )
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
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
                    text = "List", style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

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
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(it) {
                        TaskItem(
                            task = it,
                            onCheckItemClick,
                            onDeleteTaskClick,
                            onTaskItemClick
                        )
                    }
                }
            }
        }
    }
}