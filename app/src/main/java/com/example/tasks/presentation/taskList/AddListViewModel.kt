package com.example.tasks.presentation.taskList

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
class AddListViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    private val _list: MutableState<List<Task>> = mutableStateOf(emptyList())
    val list: State<List<Task>> = _list

    private var currentId: MutableState<Int> = mutableStateOf(0)

    private fun getTaskList(id: Int) = viewModelScope.launch {
        currentId.value = id
        val result = taskRepo.getTaskList(id)
        if (result is Result.Success) {
            result.data?.list?.let {
                _list.value = it
            }
        }
    }

    fun createList(
        taskList: TaskList,
        navController: NavHostController,
    ) = viewModelScope.launch {
        val result = taskRepo.createList(taskList)
        if( result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
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

    fun setCurrentId(id: Int){
        currentId.value = id
        getTaskList(id)
    }

    fun deleteList(
        id: Int,
        navController: NavHostController,
    )= viewModelScope.launch {
        val result = taskRepo.deleteList(id)
        if (result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }
}