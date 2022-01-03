package com.example.tasks.presentation.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: TaskRepo
) : ViewModel() {
    val listState: MutableState<List<TaskList>> = mutableStateOf(emptyList())
    val textState =  mutableStateOf("")

    fun search() = viewModelScope.launch {
        val result = repo.search(textState.value)
        if (result is Result.Success)
            listState.value = result.data!!
        else
            Log.d("TAG", "search: ${result.errorMessage}")
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        val result = repo.updateTask(task.copy(done = !task.done))
        if (result is Result.Success)
            search()
    }

    fun deleteTask(id: Int) = viewModelScope.launch {
        val result = repo.deleteTask(id)
        if (result is Result.Success)
            search()
    }

    fun updateText(it: String) {
        textState.value = it
    }

}