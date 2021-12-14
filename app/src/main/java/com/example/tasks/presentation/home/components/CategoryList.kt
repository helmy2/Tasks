package com.example.tasks.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList

@Composable
fun CategoryList(
    list: List<TaskList>,
    onAddClicked: () -> Unit
) {
    if (list.isNotEmpty()) {
        Column(modifier = Modifier.padding(top = 32.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "CATEGORIES")
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.clickable {
                        onAddClicked()
                    })
            }
            LazyRow {
                items(
                    list
                ) {
                    CategoryItem(taskList = it)
                }
            }
        }
    }
}