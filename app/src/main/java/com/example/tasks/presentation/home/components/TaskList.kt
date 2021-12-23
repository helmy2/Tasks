package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.Task

@Composable
fun TaskListField(
    list: List<Task>,
    onTaskItemClick: (task: Task) -> Unit,
    onDeleteItemClick: (id: Int) -> Unit
) {
    Column(modifier = Modifier.padding(top = 32.dp)) {
        Text(text = "TODAY'S TASKS", modifier = Modifier.padding(bottom = 8.dp))
        if (list.isNotEmpty()) {
            LazyColumn {
                items(list) {
                    TaskItem(task = it, onTaskItemClick, onDeleteItemClick)
                }
            }
        }
    }
}