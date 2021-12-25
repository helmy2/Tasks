package com.example.tasks.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.tasks.domain.model.Task
import com.example.tasks.presentation.util.toColor

@Composable
fun TaskItem(
    task: Task,
    onCheckItemClick: (task: Task) -> Unit,
    onDeleteItemClick: (id: Int) -> Unit,
    onTaskItemClick: (task:Task) -> Unit
) {

//    var openDialog by remember { mutableStateOf(false) }
//
//    if (openDialog) {
//        Dialog(onDismissRequest = { openDialog = false }) {
//            TaskScreen(task = task, { openDialog = false })
//        }
//    }

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
//                openDialog = true
                       onTaskItemClick(task)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = task.done,
                onClick = { onCheckItemClick(task) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = task.color.toColor(),
                    unselectedColor = task.color.toColor().copy(alpha = .5f)
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = task.title,
                textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(onClick = { onDeleteItemClick(task.id!!) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }


}
