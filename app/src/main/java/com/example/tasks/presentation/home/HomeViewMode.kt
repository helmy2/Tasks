package com.example.tasks.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.remote.models.TaskList
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor(
    private val repo: TaskRepo
) : ViewModel() {

    val data = mutableStateOf("started")

    init {
//        getAllLists()
//        getTaskList()
//        createTask()
//        updateTask()
//        deleteTask()
//        createList()
//        updateList()
//        deleteList()

        viewModelScope.launch {


        }
    }


//    private fun String.toDateLong(): Long {
//        val locale = Locale.getDefault()
//        val formatter = SimpleDateFormat.getDateTimeInstance()
//        return formatter.format(this)
//    }


    fun getAllLists() = viewModelScope.launch {
        data.value = repo.getAllLists().data.toString()
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

