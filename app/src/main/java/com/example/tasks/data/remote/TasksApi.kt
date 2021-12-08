package com.example.tasks.data.remote

import com.example.tasks.data.remote.models.Response
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.remote.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TasksApi {

    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun createAccount(
        @Body user: User
    ): Response

    @Headers("Content-Type: application/json")
    @POST("users/login")
    suspend fun login(
        @Body user: User
    ): UserResponse


}