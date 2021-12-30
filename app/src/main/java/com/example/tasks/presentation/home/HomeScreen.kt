package com.example.tasks.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.home.components.HomeField
import com.example.tasks.presentation.util.Screen
import com.google.gson.Gson

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val list = viewModel.listState.value
    val todayList = viewModel.todayListState.value
    val user = viewModel.userState.value
    val error = viewModel.errorState.value
    val logged = viewModel.loggedInState.value
    val progress = viewModel.progressState.value
    val online = viewModel.onlineState.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (error.isNotEmpty())
            Toast.makeText(LocalContext.current, error, Toast.LENGTH_SHORT).show()
        if (progress)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        else
            if (!online) {
                Text(
                    text = "You are offline",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (!logged) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(text = "You aren't logged in", style = MaterialTheme.typography.h4)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                            Text(text = "Sign In")
                        }
                    }
                } else {
                    HomeField(
                        user.name!!,
                        list,
                        todayList,
                        onProfileClicked = {
                            navController.navigate(Screen.LoginScreen.route)
                        },
                        onAddListClicked = {
                            navController.navigate(Screen.AddListScreen.route + "/-1")
                        },
                        onAddTaskClicked = {
                            navController.navigate(Screen.AddTaskScreen.route + "/-1")
                        },
                        onAddTaskItemClick = {
                            viewModel.updateTask(it.copy(done = !it.done))
                        },
                        onDeleteTaskItemClick = {
                            viewModel.deleteTask(it)
                        },
                        onListItemClick = {
                            val string = Gson().toJson(it)
                            navController.navigate(Screen.AddListScreen.route + "/$string")
                        },
                        onTaskItemClick = {
                            val string = Gson().toJson(it)
                            navController.navigate(Screen.AddTaskScreen.route + "/$string")
                        }
                    )
                }
            }
    }
}