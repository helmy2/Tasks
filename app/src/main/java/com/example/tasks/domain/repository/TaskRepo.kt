package com.example.tasks.domain.repository

import com.example.tasks.domain.model.TaskList
import com.example.tasks.data.util.Result
import com.example.tasks.domain.model.Task

interface TaskRepo {

    suspend fun createList(list: TaskList): Result<String>
    suspend fun deleteList(id: Int): Result<String>
    suspend fun getTaskList(id: Int): Result<TaskList>
    suspend fun getAllLists(): Result<List<TaskList>>
    suspend fun getTodayList(): Result<List<Task>>

    suspend fun getTask(id: Int): Result<Task>
    suspend fun updateTask(task: Task): Result<String>
    suspend fun deleteTask(id: Int): Result<String>
    suspend fun search(text: String): Result<List<TaskList>>
}