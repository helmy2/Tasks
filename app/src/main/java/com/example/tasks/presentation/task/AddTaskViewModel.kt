package com.example.tasks.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    private val _listState = mutableStateOf<List<TaskList>>(emptyList())
    val listState: State<List<TaskList>> = _listState

    init {
        getAllLists()
    }

    private fun getAllLists() = viewModelScope.launch {
        val result = taskRepo.getAllLists()
        if (result is Result.Success)
            _listState.value = result.data!!
    }

    fun createTask(
        id: Int?,
        title: String,
        description: String,
        listId: Int,
        date: Long,
        navController: NavHostController,
    ) = viewModelScope.launch {
        val result = taskRepo.updateTask(
            Task(
                id = id,
                listId = listId,
                done = false,
                title = title,
                description = description,
                color = "",
                date = date
            )
        )
        if (result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }

    fun deleteTask(id: Int, navController: NavHostController) = viewModelScope.launch {
        val result = taskRepo.deleteTask(id)
        if (result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }


}