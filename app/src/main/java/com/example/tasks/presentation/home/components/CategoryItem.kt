package com.example.tasks.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.util.toColor

@Composable
fun CategoryItem(
    taskList: TaskList,
    onListItemClick: (taskList: TaskList) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(20))
            .clickable { onListItemClick(taskList) },
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(180.dp)
        ) {
            Text(text = "${taskList.list?.count()} tasks")
            Text(text = taskList.title, style = MaterialTheme.typography.h4)
            taskList.list?.let { list ->
                LinearProgressIndicator(
                    progress = list.count { it.done } / list.count().toFloat(),
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = taskList.color.toColor()
                )
            }
        }
    }
}

