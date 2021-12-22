package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.model.Task

@Composable
fun HomeField(
    name: String,
    list: List<TaskList>,
    onProfileClicked: () -> Unit,
    onAddListClicked: () -> Unit,
    onAddTaskClicked: () -> Unit,
) {
    val taskList: MutableList<Task> = mutableListOf()
    list.forEach {
        it.list?.let {
            taskList += it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp),
        ) {

        }
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ) {
            NameField(name,onClick = onProfileClicked)
            CategoryList(list, onAddClicked = onAddListClicked)
            TaskListField(taskList.shuffled(),onAddTaskClicked)
        }
    }
}


