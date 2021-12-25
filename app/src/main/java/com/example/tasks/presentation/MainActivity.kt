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
import com.example.tasks.presentation.addList.AddListScreen
import com.example.tasks.presentation.addTask.AddTaskScreen
import com.example.tasks.presentation.home.HomeScreen
import com.example.tasks.presentation.list.ListScreen
import com.example.tasks.presentation.login.LoginScreen
import com.example.tasks.presentation.register.RegisterScreen
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
                        composable(Screen.AddListScreen.route) {
                            AddListScreen(navController)
                        }
                        composable(
                            Screen.AddTaskScreen.route + "/{id}"
                        ) {
                            val task =
                                try {
                                    Gson().fromJson(
                                        it.arguments!!.getString("id")!!,
                                        Task::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            AddTaskScreen(task, navController)
                        }
                        composable(Screen.ListScreen.route + "/{id}") {
                            val id = it.arguments?.getString("id")!!.toInt()
                            ListScreen(id, navController)
                        }
                    }
                }
            }
        }
    }
}

