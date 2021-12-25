package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.model.Task

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
    onListItemClick: (taskList:TaskList) -> Unit,
    onTaskItemClick:(task:Task) -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ) {
            NameField(name, onClick = onProfileClicked)
            CategoryList(list, onAddClicked = onAddListClicked, onListItemClick = onListItemClick)
            TaskListField(todayList, onAddTaskItemClick, onDeleteTaskItemClick,onTaskItemClick)
        }
        FloatingActionButton(
            onClick = onAddTaskClicked, contentColor = Color.White, modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }

}


