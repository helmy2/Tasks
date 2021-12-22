package com.example.tasks.presentation.addList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasks.presentation.util.ListColor
import com.example.tasks.presentation.util.colorList

@Composable
fun AddListScreen(
    navController: NavHostController, viewModel: AddListViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    var listName by remember { mutableStateOf("") }
    var listDescription by remember { mutableStateOf("") }
    var listColorIndex by remember { mutableStateOf(0) }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Text(
                text = "Add List", style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        ListColorField(listColorIndex, onItemClickListener = { listColorIndex = it })

        OutlinedTextField(
            value = listName,
            placeholder = { Text(text = "List Name", color = Color.Gray) },
            onValueChange = {
                listName = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1
        )
        OutlinedTextField(
            value = listDescription,
            placeholder = { Text(text = "List Description", color = Color.Gray) },
            onValueChange = {
                listDescription = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        Button(onClick = {
            viewModel.createList(
                listName,
                listDescription,
                colorList[listColorIndex],
                navController
            )
        }) {
            Text(text = "Save")
        }
    }


}

@Composable
fun ListColorField(
    selectedItemIndex: Int,
    onItemClickListener: (index: Int) -> Unit,
    list: List<ListColor> = colorList
) {
    LazyRow(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
    ) {

        itemsIndexed(list) { index, item ->
            RadioButton(
                selected = list[selectedItemIndex] == item,
                onClick = { onItemClickListener(index) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = item.color,
                    unselectedColor = item.color.copy(alpha = .5f)
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

