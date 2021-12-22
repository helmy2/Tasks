package com.example.tasks.presentation.addList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.presentation.util.ListColor
import com.example.tasks.presentation.util.Screen
import com.example.tasks.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddListViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    fun createList(
        title: String,
        description: String,
        color: ListColor,
        navController: NavHostController,
    ) = viewModelScope.launch {
        val result = taskRepo.createList(
            TaskList(
                id = null,
                title = title,
                description = description,
                color = color.name,
                date = getCurrentDate(),
                list = null
            )
        )
        if( result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }
}