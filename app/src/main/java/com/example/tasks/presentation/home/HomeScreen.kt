package com.example.tasks.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.home.components.HomeField
import com.example.tasks.presentation.util.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            homeState.progress -> CircularProgressIndicator()
            homeState.logged -> HomeField(
                homeState.userName,
                homeState.list,
                homeState.todayList,
                onProfileClicked = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                onAddListClicked = {
                    navController.navigate(Screen.AddListScreen.route)
                },
                onAddTaskClicked = {
                    navController.navigate(Screen.AddTaskScreen.route)
                },
                onCheckTaskItemClick = {
                    viewModel.updateTask(it.copy(done = !it.done))
                },
                onDeleteTaskItemClick = {
                    viewModel.deleteTask(it)
                },
                onListItemClick = {
                    navController.navigate(Screen.ListScreen.route + "/$it")
                }
            )
            else -> {
                Text(text = "You aren't logged in", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                    Text(text = "Sign In")
                }
            }
        }
    }
}