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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList

@Composable
fun CategoryList(
    list: List<TaskList>,
    onAddClicked: () -> Unit,
) {
    Column(modifier = Modifier.padding(top = 32.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "CATEGORIES")
            IconButton(onClick = onAddClicked) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        }
        if (list.isNotEmpty()) {
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