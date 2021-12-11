package com.example.tasks.data.repository

import com.example.tasks.data.remote.TasksApi
import com.example.tasks.data.remote.models.TaskList
import com.example.tasks.data.util.Result
import com.example.tasks.data.util.isNetworkConnected
import com.example.tasks.domain.model.Task
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.domain.util.SessionManager

class TaskRepoImpl(
    private val tasksApi: TasksApi,
    private val sessionManager: SessionManager
) : TaskRepo {

    override suspend fun createList(list: TaskList): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.createList("Bearer $token", list)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }

    override suspend fun updateList(list: TaskList): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.updateList("Bearer $token", list)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }

    override suspend fun deleteList(id: Int): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.deleteList("Bearer $token", id)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }

    override suspend fun getTaskList(id: Int): Result<TaskList> = try {
        val token = sessionManager.getJwtToken() ?: Result.Error<TaskList>("")
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<TaskList>("")
        }
        Result.Success(tasksApi.getTaskList("Bearer $token", id))
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }


    override suspend fun getAllLists(): Result<List<TaskList>> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<List<TaskList>>("No Internet connection!")
        }
        Result.Success(tasksApi.getAllLists("Bearer $token"))
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }


    override suspend fun createTask(task: Task): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.createTask("Bearer $token", task)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }


    override suspend fun updateTask(task: Task): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.updateTask("Bearer $token", task)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }

    override suspend fun deleteTask(id: Int): Result<String> = try {
        val token = sessionManager.getJwtToken() ?: ""
        if (!isNetworkConnected(sessionManager.context)) {
            Result.Error<String>("No Internet connection!")
        }
        val result = tasksApi.deleteTask("Bearer $token", id)
        if (result.success) {
            Result.Success(result.message)
        } else {
            Result.Error(result.message)
        }
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }
}