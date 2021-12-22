package com.example.tasks.presentation.addTask

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.presentation.util.ListColor
import com.example.tasks.presentation.util.Screen
import com.example.tasks.util.getCurrentDate
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
        title: String,
        description: String,
        listId: Int,
        date: Long,
        navController: NavHostController,
    ) = viewModelScope.launch {
        val result = taskRepo.createTask(
            Task(
                null,
                listId,
                false,
                title,
                description,
                "",
                date
            )
        )
        if (result is Result.Success)
            navController.navigate(Screen.HomeScreen.route)
    }

}