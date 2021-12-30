package com.example.tasks.presentation.task.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateField(context: Context, onDateChange: (date: Long) -> Unit, currantDate: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currantDate

    val locale = Locale.getDefault()
    val dateFormatter = SimpleDateFormat("yyyy.MM.dd", locale)
    val timeFormatter = SimpleDateFormat("h:mm a", locale)

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minute

            onDateChange(calendar.timeInMillis)
        }, calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], false
    )

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, day: Int ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day

            onDateChange(calendar.timeInMillis)
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
    )

    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                datePickerDialog.show()
            }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Date")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = dateFormatter.format(calendar.timeInMillis))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                timePickerDialog.show()
            }) {
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Time")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = timeFormatter.format(calendar.timeInMillis))
        }
    }
}