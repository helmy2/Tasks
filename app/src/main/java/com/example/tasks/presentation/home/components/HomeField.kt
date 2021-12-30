package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Task
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.util.FABItem
import com.example.tasks.presentation.util.MultiFloatingActionButton

@Composable
fun HomeField(
    name: String,
    list: List<TaskList>,
    todayList: List<Task>,
    onProfileClicked: () -> Unit,
    onAddListClicked: () -> Unit,
    onAddTaskClicked: () -> Unit,
    onAddTaskItemClick: (task: Task) -> Unit,
    onDeleteTaskItemClick: (id: Int) -> Unit,
    onListItemClick: (taskList: TaskList) -> Unit,
    onTaskItemClick: (task: Task) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                list = listOf(
                    FABItem("Add Task", Icons.Default.Task) {
                        onAddTaskClicked()
                    },
                    FABItem("Add List", Icons.Default.List) {
                        onAddListClicked()
                    })
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ) {
            NameField(name, onClick = onProfileClicked)
            CategoryList(list,  onListItemClick = onListItemClick)
            TaskListField(todayList, onAddTaskItemClick, onDeleteTaskItemClick, onTaskItemClick)
        }
    }

}


