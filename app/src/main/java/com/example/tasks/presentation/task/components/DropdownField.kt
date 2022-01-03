package com.example.tasks.presentation.task.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.TaskList
import com.example.tasks.presentation.util.toColor

@Composable
fun DropdownField(
    list: List<TaskList>,
    selectedIndex: Int,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    if (list.isNotEmpty())
        Box(modifier) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { expanded = true })
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "",
                    tint = list[selectedIndex].color.toColor().copy(alpha = .9f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(list[selectedIndex].title)
            }


            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEachIndexed { index, taskList ->
                    DropdownMenuItem(onClick = {
                        onItemClick(index)
                        expanded = false
                    }) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            tint = taskList.color.toColor().copy(alpha = .9f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(taskList.title, maxLines = 1)

                    }
                }
            }
        }
}