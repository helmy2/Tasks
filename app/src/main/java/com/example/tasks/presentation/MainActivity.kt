package com.example.tasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasks.presentation.login.LoginScreen
import com.example.tasks.presentation.register.RegisterScreen
import com.example.tasks.presentation.theme.TasksTheme
import com.example.tasks.presentation.util.Screen
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
                        startDestination = Screen.LoginScreen.route
                    ){
                        composable(Screen.LoginScreen.route){
                            LoginScreen(navController)
                        }
                        composable(Screen.RegisterScreen.route){
                            RegisterScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
