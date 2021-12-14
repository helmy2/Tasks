package com.example.tasks.data.remote

import com.example.tasks.data.remote.models.Response
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.model.User
import com.example.tasks.data.remote.models.UserResponse
import com.example.tasks.domain.model.Task
import retrofit2.http.*

interface TasksApi {

    @Headers("Content-Type: application/json")
    @POST("users/register")
    suspend fun createAccount(
        @Body user: User
    ): Response

    @Headers("Content-Type: application/json")
    @POST("users/login")
    suspend fun login(
        @Body user: User
    ): UserResponse

    ////////////////////// task /////////////////////

    @Headers("Content-Type: application/json")
    @POST("tasks/create")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Body task: Task
    ): Response

    @Headers("Content-Type: application/json")
    @POST("tasks/update")
    suspend fun updateTask(
        @Header("Authorization") token: String,
        @Body task: Task
    ): Response

    @Headers("Content-Type: application/json")
    @DELETE("tasks/delete")
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): Response

    ////////////////////// list /////////////////////

    @Headers("Content-Type: application/json")
    @POST("list/create")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body list: TaskList
    ): Response

    @Headers("Content-Type: application/json")
    @POST("list/update")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body list: TaskList
    ): Response

    @Headers("Content-Type: application/json")
    @DELETE("list/delete")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): Response

    @Headers("Content-Type: application/json")
    @GET("list")
    suspend fun getAllLists(
        @Header("Authorization") token: String
    ): List<TaskList>

    @Headers("Content-Type: application/json")
    @GET("list/tasks")
    suspend fun getTaskList(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): TaskList

}