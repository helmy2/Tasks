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
        getAllLists()
    }

    private fun getAllLists() = viewModelScope.launch {
        _homeState.value = HomeState(progress = true)
        val result = repo.getAllLists()
        val userResult = userRepo.getUser()
        val todayList = repo.getTodayList()
        if (
            result is Result.Success &&
            userResult is Result.Success &&
            todayList is Result.Success
        )
            _homeState.value = HomeState(
                list = result.data!!,
                todayList = todayList.data!!,
                userName = userResult.data!!.name!!,
                logged = true
            )
        else
            _homeState.value = HomeState(
                error = result.errorMessage,
            )
    }


    fun updateTask(task: Task) = viewModelScope.launch {
        val result = repo.updateTask(task)
        if (result is Result.Success)
            getAllLists()
    }


    fun deleteTask(id: Int) = viewModelScope.launch {
        val result = repo.deleteTask(id)
        if (result is Result.Success)
            getAllLists()
    }
}

