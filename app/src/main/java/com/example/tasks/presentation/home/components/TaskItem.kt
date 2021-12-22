package com.example.tasks.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.util.toColor

@Composable
fun TaskItem(task: Task) {

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = task.color.toColor().copy(.35f),
    ) {
        Text(text = task.title, modifier = Modifier.padding(16.dp).background(Color.Transparent))
    }


}