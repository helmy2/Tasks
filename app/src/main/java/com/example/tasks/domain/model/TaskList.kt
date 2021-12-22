package com.example.tasks.domain.model

data class TaskList(
    val id: Int?,
    val title: String,
    val description: String,
    val color: String,
    val date: Long,
    var list: List<Task>? = null
)
