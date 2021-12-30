package com.example.tasks.presentation.util

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MultiFloatingActionButton(
    list: List<FABItem>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val rotate by animateFloatAsState(targetValue = if (expanded) 45f else 0f)

    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier.padding(16.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = slideInVertically() + expandVertically() + fadeIn(),
            exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 4.dp)
            ) {
                list.forEach {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.title)
                        FloatingActionButton(
                            onClick = it.onItemClick,
                            modifier = Modifier.size((56 * .9f).dp)
                        ) {
                            Icon(
                                imageVector = it.imageVector,
                                contentDescription = it.title
                            )
                        }
                    }
                }
            }
        }
        FloatingActionButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

data class FABItem(
    val title: String,
    val imageVector: ImageVector,
    val onItemClick: () -> Unit,
)