package com.example.tasks.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasks.R
import com.example.tasks.domain.model.TaskList
import com.example.tasks.domain.model.Task

@Composable
fun HomeField(
    name: String,
    list: List<TaskList>,
    onProfileClicked: () -> Unit,
    onAddListClicked: () -> Unit
) {
    val taskList: MutableList<Task> = mutableListOf()
    list.forEach {
        it.list?.let {
            taskList += it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp),
        ) {
            IconButton(onClick = onProfileClicked, modifier = Modifier.align(Alignment.TopEnd)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile Icon",
                )
            }
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ) {
            NameField(name)
            CategoryList(list, onAddClicked = onAddListClicked)
            TaskList(taskList.shuffled())
        }
    }
}


