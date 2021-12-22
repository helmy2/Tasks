package com.example.tasks.data.remote.models

import com.example.tasks.domain.model.User

data class UserResponse(
    val success: Boolean,
    val user: User?,
    val message: String
)
