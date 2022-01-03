package com.example.tasks.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    LaunchedEffect(key1 = logged) {
        if (!logged)
            navController.navigate(Screen.LoginScreen.route)
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        if (error.isNotEmpty())
//            Toast.makeText(LocalContext.current, error, Toast.LENGTH_SHORT).show()
        if (progress)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        else
            if (!online) {
                Text(
                    text = "You are offline",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (logged) {
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
                    },
                    onSearchClick = {
                        navController.navigate(Screen.SearchScreen.route )
                    }

                )
            }
    }
}


