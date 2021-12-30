package com.example.tasks.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList

@Composable
fun CategoryList(
    list: List<TaskList>,
    onListItemClick: (taskList: TaskList) -> Unit

) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "CATEGORIES", modifier = Modifier.fillMaxWidth())
        if (list.isNotEmpty()) {
            LazyRow {
                items(
                    list
                ) {
                    CategoryItem(taskList = it, onListItemClick)
                }
            }
        }
    }
}