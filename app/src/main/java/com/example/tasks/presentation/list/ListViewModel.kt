package com.example.tasks.presentation.list

import androidx.compose.runtime.MutableState
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
class ListViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    private val _taskList: MutableState<TaskList?> = mutableStateOf(null)
    val taskList: State<TaskList?> = _taskList

    private var currentId: MutableState<Int> = mutableStateOf(0)

    fun getTaskList(id: Int) = viewModelScope.launch {
        currentId.value = id
        val result = taskRepo.getTaskList(id)
        if (result is Result.Success) {
            _taskList.value = result.data
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        val result = taskRepo.updateTask(task)
        if (result is Result.Success)
            getTaskList(currentId.value)
    }


    fun deleteTask(id: Int) = viewModelScope.launch {
        val result = taskRepo.deleteTask(id)
        if (result is Result.Success)
            getTaskList(currentId.value)
    }

    fun deleteList(id: Int, navController: NavHostController) = viewModelScope.launch {
        val result = taskRepo.deleteList(id)
        if (result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }

}