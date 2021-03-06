package com.example.tasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.presentation.taskList.AddListScreen
import com.example.tasks.presentation.task.AddTaskScreen
import com.example.tasks.presentation.home.HomeScreen
import com.example.tasks.presentation.login.LoginScreen
import com.example.tasks.presentation.register.RegisterScreen
import com.example.tasks.presentation.search.SearchScreen
import com.example.tasks.presentation.theme.TasksTheme
import com.example.tasks.presentation.util.Screen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(Screen.LoginScreen.route) {
                            LoginScreen(navController)
                        }
                        composable(Screen.RegisterScreen.route) {
                            RegisterScreen(navController)
                        }
                        composable(Screen.HomeScreen.route) {
                            HomeScreen(navController)

                        }
                        composable(Screen.SearchScreen.route) {
                            SearchScreen(navController)
                        }
                        composable(Screen.AddListScreen.route + "/{taskList}") {
                            val taskList =
                                try {
                                    Gson().fromJson(
                                        it.arguments!!.getString("taskList")!!,
                                        TaskList::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            AddListScreen(navController, taskList)
                        }
                        composable(
                            Screen.AddTaskScreen.route + "/{task}"
                        ) {
                            val task =
                                try {
                                    Gson().fromJson(
                                        it.arguments!!.getString("task")!!,
                                        Task::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            AddTaskScreen(task, navController)
                        }
                    }
                }
            }
        }
    }
}

