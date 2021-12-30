package com.example.tasks.presentation.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.task.components.AddTaskField

@Composable
fun AddTaskScreen(
    task: Task?,
    navController: NavHostController,
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val listState = remember { viewModel.listState }

    AddTaskField(
        navController,
        task = task,
        listState.value,
        onItemClick = { id, taskName, taskDescription, selectedId, date ->
            viewModel.createTask(
                id,
                taskName,
                taskDescription,
                selectedId,
                date,
                navController
            )

        },
        onItemDeletedClick = {
            viewModel.deleteTask(it,navController)
        }
    )
}

