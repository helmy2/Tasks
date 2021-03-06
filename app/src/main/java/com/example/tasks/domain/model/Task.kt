package com.example.tasks.domain.model

data class Task(
    val id: Int?,
    val listId: Int,
    val done: Boolean,
    val title: String,
    val description: String,
    val color: String,
    val date: Long,
)