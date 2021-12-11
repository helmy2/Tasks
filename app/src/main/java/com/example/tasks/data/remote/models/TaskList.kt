package com.example.tasks.data.remote.models

import com.example.tasks.domain.model.Task

data class TaskList(
    val id: Int?,
    val title: String,
    val description: String,
    val color: String,
    val date: Long,
    var list: List<Task>? = null
)
