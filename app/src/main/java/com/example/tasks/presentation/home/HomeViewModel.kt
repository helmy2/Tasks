package com.example.tasks.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.domain.model.TaskList
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.util.Constants.BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TaskRepo,
    private val userRepo: UserRepo,
) : ViewModel() {

    val data = mutableStateOf("started")
    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
//        getAllLists()
//        getTaskList()
//        createTask()
//        updateTask()
//        deleteTask()
//        createList()
//        updateList()
//        deleteList()

        getAllLists()

    }

    private fun getAllLists() = viewModelScope.launch {
        _homeState.value = HomeState(progress = true)
        val result = repo.getAllLists()
        val userResult = userRepo.getUser()
        if (result is Result.Success && userResult is Result.Success)
            _homeState.value = HomeState(
                list = result.data!!,
                userName = userResult.data!!.name!!,
                logged = true
            )
        else
            _homeState.value = HomeState(error = result.errorMessage)
    }

    fun getTaskList(id: Int = 3) = viewModelScope.launch {
        data.value = repo.getTaskList(id).data.toString()
    }

    fun createList(
        list: TaskList = TaskList(
            id = null,
            title = "first list title",
            description = "first description",
            color = "RED",
            date = 500,
            list = null
        )
    ) = viewModelScope.launch {
        data.value = repo.createList(list).data.toString()
    }

    fun updateList(
        list: TaskList = TaskList(
            id = 5,
            title = "first updated list title",
            description = "first description",
            color = "RED",
            date = 500,
            list = null
        )
    ) = viewModelScope.launch {
        data.value = repo.updateList(list).data.toString()
    }

    fun deleteList(
        id: Int = 4
    ) = viewModelScope.launch {
        data.value = repo.deleteList(id).data.toString()
    }


    fun createTask(
        task: Task = Task(
            id = null,
            listId = 3,
            false,
            "First Task",
            "First Task description",
            "Red",
            5000
        )
    ) = viewModelScope.launch {
        data.value = repo.createTask(task).data.toString()
    }

    fun updateTask(
        task: Task = Task(
            id = 4,
            listId = 3,
            false,
            "First Updated Task",
            "First Task description",
            "Red",
            5000
        )
    ) = viewModelScope.launch {
        data.value = repo.updateTask(task).data.toString()
    }

    fun deleteTask(
        id: Int = 6
    ) = viewModelScope.launch {
        data.value = repo.deleteTask(id).data.toString()
    }


}

