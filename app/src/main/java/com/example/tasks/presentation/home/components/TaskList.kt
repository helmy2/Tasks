package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.Task

@Composable
fun TaskList(
    list: List<Task>
) {
    Column(modifier = Modifier.padding(top = 32.dp)) {
        if (list.isNotEmpty()) {
            Text(text = "TODAY'S TASKS", modifier = Modifier.padding(bottom = 8.dp))
            LazyColumn {
                items(list) {
                    TaskItem(taskList = it)
                }
            }
        }
    }
}