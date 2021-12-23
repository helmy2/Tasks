package com.example.tasks.presentation.addList

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.addList.components.AddListField
import com.example.tasks.presentation.util.Screen
import com.example.tasks.presentation.util.colorList

@Composable
fun AddListScreen(
    navController: NavHostController, viewModel: AddListViewModel = hiltViewModel()
) {
    AddListField(
        onBackClick = {
            navController.navigate(Screen.HomeScreen.route)
        },
        onAddClick = { listName, listDescription, listColorIndex ->
            viewModel.createList(
                listName,
                listDescription,
                colorList[listColorIndex],
                navController
            )
        }
    )
}

