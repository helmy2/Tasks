package com.example.tasks.util

object Constants {

    const val JWT_TOKEN_KEY = "JWT_TOKEN_KEY"
    const val NAME_KEY = "NAME_KEY"
    const val EMAIL_KEY = "EMAIL_KEY"

    const val BASE_URL = "http://192.168.1.6:8080/v1/"

    const val MINIMUM_PASSWORD_LENGTH = 4

    const val EMAIL_REGEX =
        """^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$"""

}

