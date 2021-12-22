package com.example.tasks.presentation.util

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object AddListScreen : Screen("add_list_screen")
    object AddTaskScreen : Screen("add_task_screen")
}
