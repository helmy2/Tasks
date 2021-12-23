package com.example.tasks.presentation.home

import com.example.tasks.domain.model.Task
import com.example.tasks.domain.model.TaskList

data class HomeState(
    val error: String? = null,
    val logged: Boolean = false,
    val progress: Boolean = false,
    val userName: String = "",
    val list: List<TaskList> = emptyList(),
    val todayList: List<Task> = emptyList(),
)
