package com.example.tasks.presentation.util

import com.example.tasks.domain.util.Constants
import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    val pattern = Pattern.compile(Constants.EMAIL_REGEX)
    return (email.isNotEmpty() && pattern.matcher(email).matches())
}