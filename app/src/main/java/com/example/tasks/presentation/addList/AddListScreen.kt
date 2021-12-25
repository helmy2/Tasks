package com.example.tasks.presentation.addList

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.addList.components.AddListField
import com.example.tasks.presentation.util.Screen
import com.example.tasks.presentation.util.colorList
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

    Column {
        AddListField(
            taskList = taskList,
            list = viewModel.list.value,
            onBackClick = {
                navController.navigate(Screen.HomeScreen.route)
            },
            onAddClick = { listName, listDescription, listColorIndex ->
                viewModel.createList(
                    listName,
                    listDescription,
                    colorList[listColorIndex],
                    navController
                )
            },
            onCheckItemClick = { task ->
                viewModel.updateTask(task.copy(done = !task.done))
            }, onDeleteItemClick = { id ->
                viewModel.deleteTask(id)
            }, onTaskItemClick = { task ->
                val taskString = Gson().toJson(task)
                navController.navigate(Screen.AddTaskScreen.route + "/${taskString}")
            }
        )
    }
}

