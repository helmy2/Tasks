package com.example.tasks.data.repository

import com.example.tasks.data.remote.TasksApi
import com.example.tasks.data.remote.models.User
import com.example.tasks.data.util.Result
import com.example.tasks.data.util.isNetworkConnected
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.domain.util.SessionManager
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val tasksApi: TasksApi,
    private val sessionManager: SessionManager
) : UserRepo {

    override suspend fun createUser(user: User): Result<String> {

        return try {
            if (!isNetworkConnected(sessionManager.context)) {
                Result.Error<String>("No Internet Connection!")
            }

            val result = tasksApi.createAccount(user)
            if (result.success) {
                sessionManager.updateSession(result.message,user.name ?:"",user.email)
                Result.Success("Logged In Successfully!")
            } else {
                Result.Error(result.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "Some Problem Occurred!")
        }
    }

    override suspend fun login(user: User): Result<String> {
        return try {
            if (!isNetworkConnected(sessionManager.context)) {
                Result.Error<String>("No Internet Connection!")
            }

            val result = tasksApi.login(user)
            if (result.success) {
                result.user?.let {
                    sessionManager.updateSession(
                        token = result.message,
                        name = it.name!!,
                        email = it.email
                    )
                }
                Result.Success("Logged In Successfully!")
            } else {
                Result.Error(result.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "Some Problem Occurred!")
        }
    }

    override suspend fun getUser(): Result<User> {
        return try {
            val name = sessionManager.getCurrentUserName()
            val email = sessionManager.getCurrentUserEmail()
            if (name == null || email == null) {
                Result.Error<User>("User not Logged In!")
            }
            Result.Success(User(name, email!!, ""))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "Some Problem Occurred!")
        }
    }

    override suspend fun logout(): Result<String> {
        return try {
            sessionManager.logout()
            Result.Success("Logged Out Successfully!")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "Some Problem Occurred!")
        }
    }
}