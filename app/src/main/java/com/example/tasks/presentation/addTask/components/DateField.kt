package com.example.tasks.presentation.addTask.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.tasks.domain.util.convertLongToTime
import java.util.*

@Composable
fun DateField(context: Context, onDateChange: (date: Long) -> Unit, currantDate: Long) {
    val now = Calendar.getInstance()
    now.timeInMillis = currantDate

    val date = remember { mutableStateOf(now.timeInMillis.convertLongToTime()) }

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            now[Calendar.HOUR_OF_DAY] = hour
            now[Calendar.MINUTE] = minute

            date.value = now.timeInMillis.convertLongToTime()
        }, now[Calendar.HOUR_OF_DAY], now[Calendar.MINUTE], false
    )


    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, day: Int ->
            now[Calendar.YEAR] = year
            now[Calendar.MONTH] = month
            now[Calendar.DAY_OF_MONTH] = day

            onDateChange(now.timeInMillis)

            timePickerDialog.show()
            date.value = now.timeInMillis.convertLongToTime()

        }, now[Calendar.YEAR], now[Calendar.MONTH], now[Calendar.DAY_OF_MONTH]
    )


    Button(onClick = {
        datePickerDialog.show()
    }) {
        Text(text = "Selected date: ${date.value}")
    }
}