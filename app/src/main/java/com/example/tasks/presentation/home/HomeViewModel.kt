package com.example.tasks.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.domain.model.TaskList
import com.example.tasks.data.util.Result
import com.example.tasks.data.util.isNetworkConnected
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.User
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.domain.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TaskRepo,
    private val userRepo: UserRepo,
    sessionManager: SessionManager
) : ViewModel() {

    val listState: MutableState<List<TaskList>> = mutableStateOf(emptyList())
    val todayListState: MutableState<List<Task>> = mutableStateOf(emptyList())
    val userState: MutableState<User> = mutableStateOf(User("", "", ""))

    val errorState: MutableState<String> = mutableStateOf("")
    val loggedInState: MutableState<Boolean> = mutableStateOf(true)
    val progressState: MutableState<Boolean> = mutableStateOf(false)
    val onlineState: MutableState<Boolean> =
        mutableStateOf(isNetworkConnected(sessionManager.context))

    init {
        getUser()
        if (onlineState.value) {
            getList()
            getTodayList()
        }
    }

    private fun getUser() = viewModelScope.launch {
        val result = userRepo.getUser()
        if (result is Result.Success) {
            userState.value = result.data!!
            loggedInState.value = true
        } else {
            loggedInState.value = false
        }
    }


    private fun getTodayList() = viewModelScope.launch {
        val result = repo.getTodayList()
        if (result is Result.Success)
            todayListState.value = result.data!!
        else
            errorState.value = result.errorMessage!!
    }

    private fun getList() = viewModelScope.launch {
        val result = repo.getAllLists()
        if (result is Result.Success)
            listState.value = result.data!!
        else
            errorState.value = result.errorMessage!!
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        val result = repo.updateTask(task)
        if (result is Result.Success) {
            getTodayList()
            getList()
        } else
            errorState.value = result.errorMessage!!

    }

    fun deleteTask(id: Int) = viewModelScope.launch {
        val result = repo.deleteTask(id)
        if (result is Result.Success) {
            getTodayList()
            getList()
        } else
            errorState.value = result.errorMessage!!
    }


}

