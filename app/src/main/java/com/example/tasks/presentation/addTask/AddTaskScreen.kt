package com.example.tasks.presentation.addTask

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.addTask.components.AddTaskField

@Composable
fun AddTaskScreen(
    task: Task?,
    navController: NavHostController,
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val listState = remember { viewModel.listState }

    Column {
        AddTaskField(
            navController,
            task = task,
            listState.value,
        ) { taskName, taskDescription, selectedId, date ->
            viewModel.createTask(
                task?.id,
                taskName,
                taskDescription,
                selectedId,
                date,
                navController
            )
        }
    }

}

