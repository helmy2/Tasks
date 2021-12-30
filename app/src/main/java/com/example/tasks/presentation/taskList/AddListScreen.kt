package com.example.tasks.presentation.taskList

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.taskList.components.AddListField
import com.example.tasks.presentation.util.Screen
import com.google.gson.Gson

@Composable
fun AddListScreen(
    navController: NavHostController,
    taskList: TaskList?,
    viewModel: AddListViewModel = hiltViewModel()
) {
    taskList?.id?.let {
        viewModel.setCurrentId(it)
    }

    AddListField(
        taskList = taskList,
        list = viewModel.list.value,
        onBackClick = {
            navController.navigate(Screen.HomeScreen.route)
        },
        onAddClick = {
            viewModel.createList(
                it,
                navController
            )
        },
        onCheckItemClick = { task ->
            viewModel.updateTask(task.copy(done = !task.done))
        }, onDeleteTaskClick = { id ->
            viewModel.deleteTask(id)
        }, onTaskItemClick = { task ->
            val taskString = Gson().toJson(task)
            navController.navigate(Screen.AddTaskScreen.route + "/${taskString}")
        }, onDeleteList = { id ->
            viewModel.deleteList(id, navController)
        }
    )

}

