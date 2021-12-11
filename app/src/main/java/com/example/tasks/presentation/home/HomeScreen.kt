package com.example.tasks.presentation.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.home.components.HomeField
import com.example.tasks.presentation.util.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewMode: HomeViewMode = hiltViewModel()
) {
    val data = remember {
        viewMode.data
    }

//    Text(text = data.value)
    HomeField {
        navController.navigate(Screen.LoginScreen.route)
    }
}