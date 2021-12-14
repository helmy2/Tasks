package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.util.toColor

@Composable
fun TaskItem(taskList: Task) {
    var selected by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp,
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            RadioButton(
                selected = selected,
                onClick = { selected = !selected },
                colors = RadioButtonDefaults.colors(
                    selectedColor = taskList.color.toColor(),
                    unselectedColor = taskList.color.toColor().copy(alpha = .5f)
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = taskList.title)
        }
    }
}