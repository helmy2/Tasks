package com.example.tasks.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.home.components.TaskItem
import com.example.tasks.presentation.search.components.SearchView
import com.example.tasks.presentation.util.Screen

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val list = viewModel.listState.value
    val text = viewModel.textState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            SearchView(text) { viewModel.updateText(it) }
        }
        SearchList(list, navController, viewModel)
    }
    LaunchedEffect(key1 = text) {
        if (text.isNotBlank())
            viewModel.search()
    }
}

@Composable
fun SearchList(list: List<TaskList>, navController: NavHostController, viewModel: SearchViewModel) {
    LazyColumn() {
        list.forEach { taskList ->
            item {
                Text(text = taskList.title, style = MaterialTheme.typography.h4)
            }
            taskList.list?.let { list ->
                items(list) { task ->
                    Box(modifier = Modifier.padding(start = 36.dp)) {
                        TaskItem(
                            task = task,
                            onCheckItemClick = {
                                viewModel.updateTask(it)
                            },
                            onDeleteItemClick = {
                                viewModel.deleteTask(it)
                            },
                            onTaskItemClick = { navController.navigate(Screen.AddTaskScreen.route + "/-1") })
                    }
                }
            }
        }
    }
}


