package com.example.tasks.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.util.convertLongToTime
import com.example.tasks.presentation.home.components.TaskItem
import com.example.tasks.presentation.util.Screen
import com.google.gson.Gson

@Composable
fun ListScreen(
    id: Int,
    navController: NavHostController,
    viewModel: ListViewModel = hiltViewModel()
) {
    SideEffect {
        viewModel.getTaskList(id)
    }

    val list = viewModel.taskList.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )

    {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface),
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Text(
                text = "List Details", style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center),
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface),
                onClick = {
                    viewModel.deleteList(id, navController)
                }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }
        }
        list?.let { taskList ->
            DetailsCard("Title :", taskList.title)
            Card(
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Description :")
                    Text(text = taskList.description)
                }
            }
            DetailsCard("Color :", taskList.color)
            DetailsCard("Date :", taskList.date.convertLongToTime())
            taskList.list?.let {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    LazyColumn {
                        items(it) {
                            TaskItem(task = it, onCheckItemClick = { task ->
                                viewModel.updateTask(task.copy(done = !task.done))
                            }, onDeleteItemClick = { id ->
                                viewModel.deleteTask(id)
                            }, onTaskItemClick = { task ->
                                val taskString = Gson().toJson(task)
                                navController.navigate(Screen.AddTaskScreen.route + "/${taskString}")
                            })
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun DetailsCard(label: String, name: String) {
    Card(
        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = label)
            Text(text = name)
        }
    }
}

