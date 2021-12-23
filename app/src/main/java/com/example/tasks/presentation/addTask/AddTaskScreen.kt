package com.example.tasks.presentation.addTask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.addTask.components.AddTaskField

@Composable
fun AddTaskScreen(
    navController: NavHostController, viewModel: AddTaskViewModel = hiltViewModel()
) {

    val listState = remember { viewModel.listState }

    AddTaskField(navController, listState.value) { taskName, taskDescription, selectedId, date ->
        viewModel.createTask(
            taskName,
            taskDescription,
            selectedId,
            date,
            navController
        )
    }
}

