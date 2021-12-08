package com.example.tasks.data.remote.models

data class UserResponse(
    val success: Boolean,
    val user: User?,
    val message: String
)
