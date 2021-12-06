package com.example.tasks.domain.repository

import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result

interface UserRepo {

    suspend fun createUser(user: User): Result<String>
    suspend fun login(user:User):Result<String>
    suspend fun getUser():Result<User>
    suspend fun logout():Result<String>

}