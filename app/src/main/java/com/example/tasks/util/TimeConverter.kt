package com.example.tasks.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


fun Long.convertLongToTime(): String {
    val date = Date(this)
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat("yyyy.MM.dd", locale)
    return formatter.format(date)
}

fun String.convertDateToLong(): Long {
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat("yyyy.MM.dd", locale)
    return formatter.parse(this).time
}


fun getCurrentDate(): Long = Calendar.getInstance().time.time

